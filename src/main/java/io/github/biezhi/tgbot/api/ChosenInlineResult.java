package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChosenInlineResult implements Serializable {
    private final static long serialVersionUID = 0L;

    private String   result_id;
    private User     from;
    private Location location;
    private String   inline_message_id;
    private String   query;

}
