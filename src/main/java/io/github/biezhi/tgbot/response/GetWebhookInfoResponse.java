package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.WebhookInfo;
import lombok.Data;

@Data
public class GetWebhookInfoResponse extends BotResponse {

    private WebhookInfo result;

}
