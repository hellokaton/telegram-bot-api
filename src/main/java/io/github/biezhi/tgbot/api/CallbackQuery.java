package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class CallbackQuery implements Serializable {
    private final static long serialVersionUID = 0L;

    private String  id;
    private User    from;
    private Message message;
    private String  inline_message_id;
    private String  chat_instance;
    private String  data;
    private String  game_short_name;

}
