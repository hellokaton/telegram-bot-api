package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.ResponseParameters;
import lombok.Data;

@Data
public class BotResponse {

    private boolean            ok;
    private int                error_code;
    private String             description;
    private ResponseParameters parameters;

    public boolean isOk() {
        return ok;
    }

    public int errorCode() {
        return error_code;
    }

    public String description() {
        return description;
    }

    public ResponseParameters parameters() {
        return parameters;
    }

}
