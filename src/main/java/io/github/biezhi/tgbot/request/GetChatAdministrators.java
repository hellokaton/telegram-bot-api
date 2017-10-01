package io.github.biezhi.tgbot.request;


import io.github.biezhi.tgbot.response.GetChatAdministratorsResponse;

/**
 * Stas Parshin
 * 28 May 2016
 */
public class GetChatAdministrators extends BotRequest<GetChatAdministrators, GetChatAdministratorsResponse> {

    public GetChatAdministrators(Object chatId) {
        super(GetChatAdministratorsResponse.class);
        add("chat_id", chatId);
    }
}
