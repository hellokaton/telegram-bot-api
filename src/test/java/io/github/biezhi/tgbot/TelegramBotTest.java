package io.github.biezhi.tgbot;

import io.github.biezhi.tgbot.response.GetMeResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.Executor;

/**
 * @author biezhi
 * @date 2017/9/30
 */
@Slf4j
public class TelegramBotTest {

    private static final String TOKEN = "";

    private TelegramBot bot;

    @Before
    public void before() {
        bot = new TelegramBot(TOKEN)/*.useProxy("127.0.0.1", 1087)*/;
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
                    });
                    //.await();
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
