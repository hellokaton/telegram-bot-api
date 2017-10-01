package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseParameters implements Serializable {
    private final static long serialVersionUID = 0L;

    private Long    migrate_to_chat_id;
    private Integer retry_after;

}
