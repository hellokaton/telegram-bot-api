package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 07 May 2016
 */
public class AnswerCallbackQuery extends BotRequest<AnswerCallbackQuery, BotResponse> {

    public AnswerCallbackQuery(String callbackQueryId) {
        super(BotResponse.class);
        add("callback_query_id", callbackQueryId);
    }

    public AnswerCallbackQuery text(String text) {
        return add("text", text);
    }

    public AnswerCallbackQuery showAlert(boolean showAlert) {
        return add("show_alert", showAlert);
    }

    public AnswerCallbackQuery url(String url) {
        return add("url", url);
    }

    public AnswerCallbackQuery cacheTime(int cacheTime) {
        return add("cache_time", cacheTime);
    }
}
