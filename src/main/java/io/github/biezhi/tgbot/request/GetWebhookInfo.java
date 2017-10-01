package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.GetWebhookInfoResponse;

/**
 * Stas Parshin
 * 03 October 2016
 */
public class GetWebhookInfo extends BotRequest<GetWebhookInfo, GetWebhookInfoResponse> {

    public GetWebhookInfo() {
        super(GetWebhookInfoResponse.class);
    }

}
