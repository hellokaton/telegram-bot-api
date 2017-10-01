package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.api.request.InlineQueryResult;
import io.github.biezhi.tgbot.response.BotResponse;

/**
 * stas
 * 5/2/16.
 */
public class AnswerInlineQuery extends BotRequest<AnswerInlineQuery, BotResponse> {

    public AnswerInlineQuery(String inlineQueryId, InlineQueryResult... results) {
        super(BotResponse.class);
        add("inline_query_id", inlineQueryId).add("results", serialize(results));
    }

    public AnswerInlineQuery cacheTime(int cacheTime) {
        return add("cache_time", cacheTime);
    }

    public AnswerInlineQuery isPersonal(boolean isPersonal) {
        return add("is_personal", isPersonal);
    }

    public AnswerInlineQuery nextOffset(String nextOffset) {
        return add("next_offset", nextOffset);
    }

    public AnswerInlineQuery switchPmText(String switchPmText) {
        return add("switch_pm_text", switchPmText);
    }

    public AnswerInlineQuery switchPmParameter(String switchPmParameter) {
        return add("switch_pm_parameter", switchPmParameter);
    }

}
