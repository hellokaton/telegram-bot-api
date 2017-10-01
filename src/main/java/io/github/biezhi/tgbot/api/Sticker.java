package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Sticker implements Serializable {
    private final static long serialVersionUID = 0L;

    private String       file_id;
    private Integer      width;
    private Integer      height;
    private PhotoSize    thumb;
    private String       emoji;
    private String       set_name;
    private MaskPosition mask_position;
    private Integer      file_size;

}
