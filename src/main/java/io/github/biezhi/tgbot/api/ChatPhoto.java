package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatPhoto implements Serializable {
    private final static long serialVersionUID = 0L;

    private String small_file_id;
    private String big_file_id;

}
