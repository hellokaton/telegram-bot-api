package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

@Data
public class UserProfilePhotos implements Serializable {
    private final static long serialVersionUID = 0L;

    private Integer       total_count;
    private PhotoSize[][] photos;

}