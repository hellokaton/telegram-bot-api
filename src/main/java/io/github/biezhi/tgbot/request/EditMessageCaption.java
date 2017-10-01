package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.api.request.InlineKeyboardMarkup;
import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 07 May 2016
 */
public class EditMessageCaption extends BotRequest<EditMessageCaption, BotResponse> {

    public EditMessageCaption(Object chatId, int messageId, String text) {
        super(BotResponse.class);
        add("chat_id", chatId).add("message_id", messageId).add("text", text);
    }

    public EditMessageCaption(String inlineMessageId, String text) {
        super(BotResponse.class);
        add("inline_message_id", inlineMessageId).add("text", text);
    }

    public EditMessageCaption caption(String caption) {
        return add("caption", caption);
    }

    public EditMessageCaption replyMarkup(InlineKeyboardMarkup replyMarkup) {
        return add("reply_markup", replyMarkup);
    }

}
