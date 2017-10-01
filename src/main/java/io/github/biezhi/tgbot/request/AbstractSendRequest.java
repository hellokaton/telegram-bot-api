package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.api.request.Keyboard;
import io.github.biezhi.tgbot.response.SendResponse;

/**
 * stas
 * 5/1/16.
 */
abstract public class AbstractSendRequest<T extends AbstractSendRequest> extends BotRequest<T, SendResponse> {

    public AbstractSendRequest(Object chatId) {
        super(SendResponse.class);
        add("chat_id", chatId);
    }

    public T disableNotification(boolean disableNotification) {
        return add("disable_notification", disableNotification);
    }

    public T replyToMessageId(int replyToMessageId) {
        return add("reply_to_message_id", replyToMessageId);
    }

    public T replyMarkup(Keyboard replyMarkup) {
        return add("reply_markup", replyMarkup);
    }
}
