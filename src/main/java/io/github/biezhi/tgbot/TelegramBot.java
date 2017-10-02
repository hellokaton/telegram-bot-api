package io.github.biezhi.tgbot;

import com.google.gson.Gson;
import io.github.biezhi.request.Request;
import io.github.biezhi.tgbot.api.*;
import io.github.biezhi.tgbot.request.*;
import io.github.biezhi.tgbot.response.BotResponse;
import io.github.biezhi.tgbot.response.GetMeResponse;
import io.github.biezhi.tgbot.response.GetUpdatesResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.net.Proxy.Type.HTTP;

/**
 * Telegram机器人
 *
 * @author biezhi
 * @date 2017/9/30
 */
@Slf4j
public class TelegramBot {

    private Gson gson = new Gson();
    private          String  token;
    private          Proxy   proxy;
    private          FileApi fileApi;
    private volatile boolean startUpdates;
    private volatile boolean stopUpdates;
    private Map<String, Consumer<Message>> textMappings    = new HashMap<>();
    private Consumer<Message>              stickerMapping  = null;
    private Consumer<Message>              othersMapping   = null;
    private Options                        options         = Options.builder().build();
    private ExecutorService                executorService = Executors.newFixedThreadPool(options.getExecutorPoolSize());

    public TelegramBot(String token) {
        this.token = token;
        this.fileApi = new FileApi(token);
    }

    /**
     * 设置自定义配置
     *
     * @param options
     * @return
     */
    public TelegramBot options(Options options) {
        this.options = options;
        this.executorService = Executors.newFixedThreadPool(options.getExecutorPoolSize());
        return this;
    }

    /**
     * 启动机器人后发的消息
     *
     * @param consumer
     * @return
     */
    public TelegramBot onStart(Consumer<Message> consumer) {
        return this.onCmd("/start", consumer);
    }

    /**
     * 输入 /help 指令后
     *
     * @param consumer
     * @return
     */
    public TelegramBot onHelp(Consumer<Message> consumer) {
        return this.onCmd("/help", consumer);
    }

    /**
     * 监听接收到贴纸
     *
     * @param consumer
     * @return
     */
    public TelegramBot onSticker(Consumer<Message> consumer) {
        this.stickerMapping = consumer;
        this.start();
        return this;
    }

    /**
     * 监听除指令、贴纸、视频、图片、音频之外的消息
     *
     * @param consumer
     * @return
     */
    public TelegramBot onOthers(Consumer<Message> consumer) {
        this.othersMapping = consumer;
        this.start();
        return this;
    }

    /**
     * 监听指令
     *
     * @param cmdText  指令文本
     * @param consumer 处理器
     * @return
     */
    public TelegramBot onCmd(String cmdText, Consumer<Message> consumer) {
        if (textMappings.containsKey(cmdText)) {
            throw new BotException("请不要重复监听相同指令.");
        }
        textMappings.put(cmdText, consumer);
        this.start();
        return this;
    }

    private TelegramBot start() {
        if (!startUpdates) {
            log.info("Start listen bot message :)");
            startUpdates = true;
            GetUpdates getUpdates = new GetUpdates();
            this.getUpdates(getUpdates);
        }
        return this;
    }

    /**
     * 设置代理
     *
     * @param host 代理主机
     * @param port 代理端口
     * @return
     */
    public TelegramBot useProxy(String host, int port) {
        this.proxy = new java.net.Proxy(HTTP, new InetSocketAddress(host, port));
        return this;
    }

    /**
     * 设置代理
     *
     * @param proxy
     * @return
     */
    public TelegramBot useProxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    /**
     * 返回 GetMe
     *
     * @return
     */
    public GetMeResponse getMe() {
        GetMe request = new GetMe();
        return this.execute(request);
    }

    /**
     * 发送文本消息
     *
     * @param message
     * @param text
     */
    public void text(Message message, String text) {
        this.text(message.chatId(), text);
    }

    /**
     * 发送文本消息
     *
     * @param chatId
     * @param message
     */
    public void text(String chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        this.execute(sendMessage);
    }

    /**
     * 发送图片
     *
     * @param message
     * @param photos
     */
    public void photo(Message message, String... photos) {
        this.photo(message.chatId(), photos);
    }

    /**
     * 发送图片
     *
     * @param chatId
     * @param photos 这里是图片的URL链接，多个用逗号相隔开
     */
    public void photo(String chatId, String... photos) {
        for (String photo : photos) {
            SendPhoto sendPhoto = new SendPhoto(chatId, photo);
            this.execute(sendPhoto);
        }
    }

    /**
     * 发送图片
     *
     * @param message
     * @param file
     */
    public void photo(Message message, java.io.File file) {
        SendPhoto sendPhoto = new SendPhoto(message.chatId(), file);
        this.execute(sendPhoto);
    }

    private void getUpdates(GetUpdates request) {
        if (stopUpdates) {
            return;
        }
        this.executeAsync(request, new Callback<GetUpdates, GetUpdatesResponse>() {
            @Override
            public void onSuccess(GetUpdates request, GetUpdatesResponse response) {
                if (!response.isOk() || response.getResult() == null || response.getResult().size() <= 0) {
                    sleep();
                    getUpdates(request);
                    return;
                }
                List<Update> updates = response.getResult();
                int          offset  = lastUpdateId(updates) + 1;

                if (!textMappings.isEmpty()) {
                    Set<String> texts = textMappings.keySet();
                    List<Message> onTextUpdates = updates.stream()
                            .map(Update::getMessage)
                            .filter(message -> texts.contains(message.getText()))
                            .collect(Collectors.toList());

                    updates.retainAll(onTextUpdates);
                    onTextUpdates.stream().parallel().forEach(message -> textMappings.get(message.getText()).accept(message));
                }

                if (null != stickerMapping) {
                    List<Message> onStickerUpdates = updates.stream()
                            .map(Update::getMessage)
                            .filter(message -> null != message.getSticker())
                            .collect(Collectors.toList());

                    updates.retainAll(onStickerUpdates);
                    onStickerUpdates.stream().parallel().forEach(message -> stickerMapping.accept(message));
                }

                if (null != othersMapping && updates.size() > 0) {
                    updates.stream().parallel().map(Update::getMessage).forEach(message -> othersMapping.accept(message));
                }

                request = request.offset(offset);
                sleep();
                getUpdates(request);
            }

            @Override
            public void onFailure(GetUpdates request, Throwable e) {
                log.error("调用API出现异常", e);
                sleep();
                getUpdates(request);
            }
        });

    }

    private int lastUpdateId(List<Update> updates) {
        if (null == updates || updates.isEmpty()) {
            return 0;
        }
        return updates.get(updates.size() - 1).getUpdate_id();
    }

    /**
     * 休眠，单位毫秒
     */
    private void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(options.getDuration());
        } catch (InterruptedException e) {
            log.error("休眠出现异常", e);
        }
    }

    /**
     * 同步执行请求
     *
     * @param request
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    public <T extends BotRequest, R extends BotResponse> R execute(T request) {
        if (null == token || "".equals(token)) {
            throw new BotException("请确认您机器人Token已填写");
        }
        String url = String.format(Const.API_URL + "%s/%s", token, request.getMethod());
        if (options.isDebug()) {
            log.info("Request : {}", url);
            log.info("Params  : {}", this.toJson(request.getParameters()));
        }
        String body;

        Request httpRequest = Request.post(url)
                .useProxy(this.proxy)
                .contentType("application/json")
                .connectTimeout(30_000)
                .readTimeout(10_000);

        if (request.isMultipart()) {
            request.getParameters().forEach((key, value) -> {
                if (value instanceof byte[]) {
                    httpRequest.part(key.toString(), request.getFileName(), request.getContentType(), new ByteArrayInputStream((byte[]) value));
                } else if (value instanceof java.io.File) {
                    httpRequest.part(key.toString(), request.getFileName(), request.getContentType(), (java.io.File) value);
                } else {
                    httpRequest.part(key.toString(), value.toString());
                }
            });
            body = httpRequest.body();
        } else {
            body = httpRequest.send(this.toJson(request.getParameters()).getBytes()).body();
        }
        if (options.isDebug()) {
            log.info("Response: {}", body);
        }
        R response = gson.fromJson(body, request.getResponseType());
        return response;
    }

    /**
     * 异步执行一个请求
     *
     * @param request 请求对象
     * @param <T>     请求类型
     * @param <R>     响应类型
     * @return
     */
    public <T extends BotRequest, R extends BotResponse> void executeAsync(T request, Callback<T, R> callback) {
        CompletableFuture.supplyAsync(() -> {
            R response = this.execute(request);
            return response;
        }, executorService).whenComplete((r, throwable) -> {
            if (null == throwable) {
                callback.onSuccess(request, r);
            } else {
                callback.onFailure(request, throwable);
            }
        });

//        executorService.submit(() -> {
//            try {
//                R response = this.execute(request);
//                callback.onSuccess(request, response);
//            } catch (Exception e) {
//                callback.onFailure(request, e);
//            }
//        });
    }

    public String getFullFilePath(FileItem file) {
        return fileApi.getFullFilePath(file.getFile_path());
    }

    public void stop() {
        this.stopUpdates = true;
    }

    public void await() {
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            log.error("Join异常", e);
        }
    }

    public String toJson(Object bean) {
        return gson.toJson(bean);
    }

}