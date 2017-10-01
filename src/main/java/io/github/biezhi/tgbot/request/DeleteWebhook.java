package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 07 December 2016
 */
public class DeleteWebhook extends BotRequest<DeleteWebhook, BotResponse> {

    public DeleteWebhook() {
        super(BotResponse.class);
    }
}
