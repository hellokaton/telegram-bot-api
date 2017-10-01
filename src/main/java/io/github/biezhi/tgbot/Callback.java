package io.github.biezhi.tgbot;

import io.github.biezhi.tgbot.request.BotRequest;
import io.github.biezhi.tgbot.response.BotResponse;

/**
 * @author biezhi
 * @date 2017/10/1
 */
public interface Callback<T extends BotRequest, R extends BotResponse> {

    void onSuccess(T request, R response);

    void onFailure(T request, Throwable t);

}