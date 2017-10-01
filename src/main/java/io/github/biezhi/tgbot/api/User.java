package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private final static long serialVersionUID = 0L;

    private Integer id;
    private Boolean is_bot;
    private String first_name;
    private String last_name;
    private String username;
    private String language_code;

}