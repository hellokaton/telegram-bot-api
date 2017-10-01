package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.api.request.InlineKeyboardMarkup;
import io.github.biezhi.tgbot.api.request.ParseMode;
import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 07 May 2016
 */
public class EditMessageText extends BotRequest<EditMessageText, BotResponse> {

    public EditMessageText(Object chatId, int messageId, String text) {
        super(BotResponse.class);
        add("chat_id", chatId).add("message_id", messageId).add("text", text);
    }

    public EditMessageText(String inlineMessageId, String text) {
        super(BotResponse.class);
        add("inline_message_id", inlineMessageId).add("text", text);
    }

    public EditMessageText parseMode(ParseMode parseMode) {
        return add("parse_mode", parseMode.name());
    }

    public EditMessageText disableWebPagePreview(boolean disableWebPagePreview) {
        return add("disable_web_page_preview", disableWebPagePreview);
    }

    public EditMessageText replyMarkup(InlineKeyboardMarkup replyMarkup) {
        return add("reply_markup", replyMarkup);
    }

}
