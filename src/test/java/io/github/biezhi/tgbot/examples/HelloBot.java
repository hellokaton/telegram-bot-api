package io.github.biezhi.tgbot.examples;

import io.github.biezhi.tgbot.Options;
import io.github.biezhi.tgbot.TelegramBot;

/**
 * @author biezhi
 * @date 2017/10/1
 */
public class HelloBot {

    public static void main(String[] args) {
        TelegramBot bot = new TelegramBot("").useProxy("127.0.0.1", 1087);
//        bot.options(Options.builder().debug(true).build());
        bot.onStart(message -> bot.text(message, "欢迎使用Hello Bot.")).await();
    }

}
