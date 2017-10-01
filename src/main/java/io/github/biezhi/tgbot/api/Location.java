package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Location implements Serializable {
    private final static long serialVersionUID = 0L;

    private Float longitude;
    private Float latitude;

}
