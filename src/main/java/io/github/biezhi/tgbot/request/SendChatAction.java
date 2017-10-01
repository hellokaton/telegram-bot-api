package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.api.request.ChatAction;
import io.github.biezhi.tgbot.response.BotResponse;

/**
 * stas
 * 5/2/16.
 */
public class SendChatAction extends BotRequest<SendChatAction, BotResponse> {

    public SendChatAction(Object chatId, String action) {
        super(BotResponse.class);
        add("chat_id", chatId).add("action", action);
    }

    public SendChatAction(Object chatId, ChatAction action) {
        super(BotResponse.class);
        add("chat_id", chatId).add("action", action.name());
    }
}
