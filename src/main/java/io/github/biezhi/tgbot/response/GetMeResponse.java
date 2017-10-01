package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.User;
import lombok.Data;

@Data
public class GetMeResponse extends BotResponse {

    private User result;

}
