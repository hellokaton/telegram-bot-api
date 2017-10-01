package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Voice implements Serializable {
    private final static long serialVersionUID = 0L;

    private String file_id;
    private Integer duration;
    private String mime_type;
    private Integer file_size;

}