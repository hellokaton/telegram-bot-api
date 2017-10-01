package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 28 May 2016
 */
public class GetChat extends BotRequest<GetChat, BotResponse> {

    public GetChat(Object chatId) {
        super(BotResponse.class);
        add("chat_id", chatId);
    }
}
