package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageEntity implements Serializable {
    private final static long serialVersionUID = 0L;

    public enum Type {
        mention, hashtag, bot_command, url, email, bold, italic, code, pre, text_link, text_mention;
    }

    private Type    type;
    private Integer offset;
    private Integer length;
    private String  url;
    private User    user;

}
