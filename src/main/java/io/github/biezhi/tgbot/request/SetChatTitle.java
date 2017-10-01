package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 01 July 2017
 */
public class SetChatTitle extends BotRequest<SetChatTitle, BotResponse> {

    public SetChatTitle(Object chatId, String title) {
        super(BotResponse.class);
        add("chat_id", chatId).add("title", title);
    }
}
