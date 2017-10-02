package io.github.biezhi.tgbot;

import io.github.biezhi.tgbot.api.request.ReplyKeyboardMarkup;
import io.github.biezhi.tgbot.request.GetFile;
import io.github.biezhi.tgbot.request.GetStickerSet;
import io.github.biezhi.tgbot.request.SendMessage;
import io.github.biezhi.tgbot.response.GetFileResponse;
import io.github.biezhi.tgbot.response.GetMeResponse;
import io.github.biezhi.tgbot.response.GetStickerSetResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * 机器人测试代码
 * <p>
 * 1. 请填写你申请的机器人Token
 * 2. 将注释 await 代码打开
 *
 * @author biezhi
 * @date 2017/9/30
 */
@Slf4j
public class TelegramBotTest {

    private static final String TOKEN = "";

    private TelegramBot bot;

    @Before
    public void before() {
        bot = new TelegramBot(TOKEN)
                .options(Options.builder().debug(true).readTimeout(30_000L).build())
                .useProxy("127.0.0.1", 1087);
    }

    @Test
    public void testGetMe() {
        try {
            GetMeResponse response = bot.getMe();
            log.info("GetMe: {}", response);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @Test
    public void testOnStart() {
        try {
            bot.onStart(message -> bot.text(message, "欢迎使用XX机器人。"));
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @Test
    public void testMessage() throws InterruptedException {
        try {
            bot.onHelp(message -> {
                log.info("{}", message);
                bot.text(message, "/echo\r\n/me\r\n/hi");
            })
                    .onCmd("/echo", message -> {
                        log.info("{}", message);
                        bot.text(message, "Hi, " + message.getFrom().getUsername() + ". I,m fine.");
                    })
                    .onCmd("/hi", message -> bot.text(message, "Hi"))
                    .onCmd("/me", message -> bot.text(message, bot.toJson(bot.getMe())))
                    .onCmd("/img", message -> {
                        log.info("收到图片请求");
                        bot.photo(message, new File("/Users/biezhi/Pictures/20150812204022.jpeg"));
                    })
                    .onCmd("kbd", message -> {
                        String[]            key                 = {"🌝", "🌚"};
                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(key);
                        SendMessage         sendMessage         = new SendMessage(message.chatId(), "请选择一个表情");
                        sendMessage.replyMarkup(replyKeyboardMarkup);
                        bot.execute(sendMessage);
                    })
                    .onCmd("🌚", message -> bot.text(message, "你选择了小黑"))
                    .await();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @Test
    public void testDownLoad() {
        GetFile         getFile         = new GetFile("CAADBQADMAADSZ7ACjho6kUMMDxaAg");
        GetFileResponse getFileResponse = bot.execute(getFile);
        String          fileLink        = bot.getFullFilePath(getFileResponse.getResult());
        System.out.println(fileLink);
    }

    /**
     * 获取StickerSet的思路
     * <p>
     * 1、向Bot发送一张贴纸
     * 2、根据该贴纸获取贴纸名称（sticker.set_name）
     * 3、根据set_name获取StickerSet
     * 4、遍历StickerSet中的贴纸，逐个进行下载
     * 5、调用webp-io将贴纸转换为png图片
     */
    @Test
    public void testGetStickerSet() {
        GetStickerSet         getStickerSet      = new GetStickerSet("miaomiaomiao2");
        GetStickerSetResponse stickerSetResponse = bot.execute(getStickerSet);
        System.out.println(stickerSetResponse);
    }

}
