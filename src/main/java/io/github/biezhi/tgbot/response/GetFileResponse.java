package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.File;
import lombok.Data;

@Data
public class GetFileResponse extends BotResponse {

    private File result;

}
