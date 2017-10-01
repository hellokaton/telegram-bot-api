package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.Chat;
import lombok.Data;

@Data
public class GetChatResponse extends BotResponse {

    private Chat result;

}
