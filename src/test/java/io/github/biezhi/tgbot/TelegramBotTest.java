package io.github.biezhi.tgbot;

import io.github.biezhi.tgbot.response.GetMeResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author biezhi
 * @date 2017/9/30
 */
@Slf4j
public class TelegramBotTest {

    private static final String TOKEN = "YOUR BOT TOKEN";

    private TelegramBot bot;

    @Before
    public void before() {
        bot = new TelegramBot(TOKEN).useProxy("127.0.0.1", 1087);
    }

    @Test
    public void testGetMe() {
        GetMeResponse response = bot.getMe();
        log.info("GetMe: {}", response);
    }

    @Test
    public void testMessage() throws InterruptedException {
        bot.onCmd("/help", message -> {
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
            .await();
    }

}
