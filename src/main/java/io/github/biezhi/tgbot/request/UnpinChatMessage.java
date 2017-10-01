package io.github.biezhi.tgbot.request;


import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 01 July 2017
 */
public class UnpinChatMessage extends BotRequest<UnpinChatMessage, BotResponse> {

    public UnpinChatMessage(Object chatId) {
        super(BotResponse.class);
        add("chat_id", chatId);
    }
}
