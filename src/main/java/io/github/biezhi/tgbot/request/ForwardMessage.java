package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * stas
 * 5/1/16.
 */
public class ForwardMessage extends BotRequest<ForwardMessage, BotResponse> {

    public ForwardMessage(Object chatId, Object fromChatId, int messageId) {
        super(BotResponse.class);
        add("chat_id", chatId).add("from_chat_id", fromChatId).add("message_id", messageId);
    }

    public ForwardMessage disableNotification(boolean disableNotification) {
        return add("disable_notification", disableNotification);
    }
}
