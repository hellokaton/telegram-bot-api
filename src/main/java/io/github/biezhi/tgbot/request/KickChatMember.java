package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * stas
 * 5/2/16.
 */
public class KickChatMember extends BotRequest<KickChatMember, BotResponse> {

    public KickChatMember(Object chatId, int userId) {
        super(BotResponse.class);
        add("chat_id", chatId).add("user_id", userId);
    }

    public KickChatMember untilDate(int untilDate) {
        return add("until_date", untilDate);
    }
}
