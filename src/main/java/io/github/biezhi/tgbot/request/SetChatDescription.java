package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 01 July 2017
 */
public class SetChatDescription extends BotRequest<SetChatDescription, BotResponse> {

    public SetChatDescription(Object chatId, String description) {
        super(BotResponse.class);
        add("chat_id", chatId).add("description", description);
    }
}
