package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Animation implements Serializable {
    private final static long serialVersionUID = 0L;

    private String    file_id;
    private PhotoSize thumb;
    private String    file_name;
    private String    mime_type;
    private Integer   file_size;

}
