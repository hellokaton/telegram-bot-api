package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

@Data
public class Game implements Serializable {
    private final static long serialVersionUID = 0L;

    private String      title;
    private String      description;
    private PhotoSize[] photo;

    private String          text;
    private MessageEntity[] text_entities;
    private Animation       animation;

}
