package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.GetChatMembersCountResponse;

/**
 * Stas Parshin
 * 28 May 2016
 */
public class GetChatMembersCount extends BotRequest<GetChatMembersCount, GetChatMembersCountResponse> {

    public GetChatMembersCount(Object chatId) {
        super(GetChatMembersCountResponse.class);
        add("chat_id", chatId);
    }
}
