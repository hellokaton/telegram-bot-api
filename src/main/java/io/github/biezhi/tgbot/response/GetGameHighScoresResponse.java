package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.GameHighScore;
import lombok.Data;

@Data
public class GetGameHighScoresResponse extends BotResponse {

    private GameHighScore[] result;

    public GameHighScore[] result() {
        return result;
    }

}
