package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class InlineQuery implements Serializable {
    private final static long serialVersionUID = 0L;

    private String   id;
    private User     from;
    private Location location;
    private String   query;
    private String   offset;

}
