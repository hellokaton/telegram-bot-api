package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.GetChatMemberResponse;

/**
 * Stas Parshin
 * 28 May 2016
 */
public class GetChatMember extends BotRequest<GetChatMember, GetChatMemberResponse> {

    public GetChatMember(Object chatId, int userId) {
        super(GetChatMemberResponse.class);
        add("chat_id", chatId).add("user_id", userId);
    }
}
