package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 26 May 2017
 */
public class AnswerPreCheckoutQuery extends BotRequest<AnswerPreCheckoutQuery, BotResponse> {

    public AnswerPreCheckoutQuery(String preCheckoutQueryId) {
        super(BotResponse.class);
        add("pre_checkout_query_id", preCheckoutQueryId).add("ok", true);
    }

    public AnswerPreCheckoutQuery(String preCheckoutQueryId, String errorMessage) {
        super(BotResponse.class);
        add("pre_checkout_query_id", preCheckoutQueryId).add("ok", false).add("error_message", errorMessage);
    }
}
