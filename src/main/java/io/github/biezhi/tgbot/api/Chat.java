package io.github.biezhi.tgbot.api;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class Chat implements Serializable {
    private final static long serialVersionUID = 0L;

    public enum Type {
        @SerializedName("private")Private, group, supergroup, channel
    }

    private Long id;
    private Type type;

    //Private
    private String first_name;
    private String last_name;

    //Private and Channel
    private String username;

    //Channel and Group
    private String title;

    private Boolean all_members_are_administrators;

    private ChatPhoto photo;
    private String    description;
    private String    invite_link;
    private Message   pinned_message;

}
