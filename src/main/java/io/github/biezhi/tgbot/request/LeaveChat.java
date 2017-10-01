package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 28 May 2016
 */
public class LeaveChat extends BotRequest<LeaveChat, BotResponse> {

    public LeaveChat(Object chatId) {
        super(BotResponse.class);
        add("chat_id", chatId);
    }
}
