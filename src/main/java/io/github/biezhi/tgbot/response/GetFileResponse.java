package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.FileItem;
import lombok.Data;

@Data
public class GetFileResponse extends BotResponse {

    private FileItem result;

}
