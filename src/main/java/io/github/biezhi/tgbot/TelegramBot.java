package io.github.biezhi.tgbot;

import com.google.gson.Gson;
import io.github.biezhi.request.Request;
import io.github.biezhi.tgbot.api.File;
import io.github.biezhi.tgbot.api.FileApi;
import io.github.biezhi.tgbot.api.Message;
import io.github.biezhi.tgbot.api.Update;
import io.github.biezhi.tgbot.request.*;
import io.github.biezhi.tgbot.response.BotResponse;
import io.github.biezhi.tgbot.response.GetMeResponse;
import io.github.biezhi.tgbot.response.GetUpdatesResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static java.net.Proxy.Type.HTTP;

/**
 * @author biezhi
 * @date 2017/9/30
 */
@Slf4j
public class TelegramBot {

    private Gson gson = new Gson();
    private          String  token;
    private          Proxy   proxy;
    private          boolean startUpdates;
    private          FileApi fileApi;
    private volatile boolean stopUpdates;
    private Map<String, Consumer<Message>> mappings        = new HashMap<>();
    private Options                        options         = new Options();
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

    public TelegramBot onCmd(String text, Consumer<Message> consumer) {
        mappings.put(text, consumer);
        if (!startUpdates) {
            executorService.execute(() -> {
                startUpdates = true;
                GetUpdates getUpdates = new GetUpdates();
                this.getUpdates(getUpdates);
            });
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
        GetUpdatesResponse response = this.executeCommand(request);
        if (!response.isOk() || response.getResult() == null || response.getResult().size() <= 0) {
            this.sleep();
            getUpdates(request);
            return;
        }
        List<Update> updates = response.getResult();
        if (!mappings.isEmpty()) {
            Set<String> texts = mappings.keySet();
            updates.stream()
                    .map(Update::getMessage)
                    .filter(message -> texts.contains(message.getText()))
                    .forEach(message -> mappings.get(message.getText()).accept(message));

            int offset = lastUpdateId(updates) + 1;
            request = request.offset(offset);
        }
        sleep();
        getUpdates(request);
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
     * 执行一个请求
     *
     * @param request 请求对象
     * @param <T>     请求类型
     * @param <R>     响应类型
     * @return
     */
    public <T extends BotRequest, R extends BotResponse> R execute(BotRequest<T, R> request) {
        return this.executeCommand(request);
    }

    private <R extends BotResponse> R executeCommand(BotRequest request) {
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
        R result = gson.fromJson(body, request.getResponseType());
        return result;
    }

    public String getFullFilePath(File file) {
        return fileApi.getFullFilePath(file.getFile_path());
    }

    public void stop() {
        this.stopUpdates = true;
    }

    public void await() {
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toJson(Object bean) {
        return gson.toJson(bean);
    }

}