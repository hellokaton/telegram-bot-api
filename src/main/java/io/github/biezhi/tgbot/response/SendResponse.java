package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.Message;
import lombok.Data;

@Data
public class SendResponse extends BotResponse {

    private Message result;

}
