package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Audio implements Serializable {
    private final static long serialVersionUID = 0L;

    private String file_id;
    private Integer duration;
    private String performer;
    private String title;
    private String mime_type;
    private Integer file_size;

}
