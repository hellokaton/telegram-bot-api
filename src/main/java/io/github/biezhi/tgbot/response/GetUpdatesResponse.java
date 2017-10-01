package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.Update;
import lombok.Data;

import java.util.List;

@Data
public class GetUpdatesResponse extends BotResponse {

    private List<Update> result;

}