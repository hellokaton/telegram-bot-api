package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 01 July 2017
 */
public class PinChatMessage extends BotRequest<PinChatMessage, BotResponse> {

    public PinChatMessage(Object chatId, int messageId) {
        super(BotResponse.class);
        add("chat_id", chatId).add("message_id", messageId);
    }

    public PinChatMessage disableNotification(boolean disableNotification) {
        return add("disable_notification", disableNotification);
    }
}
