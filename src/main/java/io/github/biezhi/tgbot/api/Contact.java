package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Contact implements Serializable {
    private final static long serialVersionUID = 0L;

    private String phone_number;
    private String first_name;
    private String last_name;
    private Integer user_id;

}
