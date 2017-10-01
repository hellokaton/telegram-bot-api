package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.GetMeResponse;

/**
 * stas
 * 5/1/16.
 */
public class GetMe extends BotRequest<GetMe, GetMeResponse> {

    public GetMe() {
        super(GetMeResponse.class);
    }
}
