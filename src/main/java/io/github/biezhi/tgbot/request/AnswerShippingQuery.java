package io.github.biezhi.tgbot.request;

import com.google.gson.Gson;
import io.github.biezhi.tgbot.api.request.ShippingOption;
import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 25 May 2017
 */
public class AnswerShippingQuery extends BotRequest<AnswerShippingQuery, BotResponse> {

    private static Gson gson = new Gson();

    public AnswerShippingQuery(String shippingQueryId, ShippingOption... shippingOptions) {
        super(BotResponse.class);
        add("shipping_query_id", shippingQueryId).add("ok", true).add("shipping_options", serialize(shippingOptions));
    }

    public AnswerShippingQuery(String shippingQueryId, String errorMessage) {
        super(BotResponse.class);
        add("shipping_query_id", shippingQueryId).add("ok", false).add("error_message", errorMessage);
    }
}
