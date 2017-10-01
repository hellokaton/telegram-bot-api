package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameHighScore implements Serializable {
    private final static long serialVersionUID = 0L;

    private Integer position;
    private User    user;
    private Integer score;

}
