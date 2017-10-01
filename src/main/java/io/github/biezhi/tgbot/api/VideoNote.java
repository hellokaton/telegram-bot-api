package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoNote implements Serializable {
    private final static long serialVersionUID = 0L;

    private String    file_id;
    private Integer   length;
    private Integer   duration;
    private PhotoSize thumb;
    private Integer   file_size;

}
