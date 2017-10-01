package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 22 May 2017
 */
public class DeleteMessage extends BotRequest<DeleteMessage, BotResponse> {

    public DeleteMessage(Object chatId, int messageId) {
        super(BotResponse.class);
        add("chat_id", chatId).add("message_id", messageId);
    }
}
