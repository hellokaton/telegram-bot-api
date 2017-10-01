package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatMember implements Serializable {
    private final static long serialVersionUID = 0L;

    public enum Status {
        creator, administrator, member, restricted, left, kicked;
    }

    private User   user;
    private Status status;

    private Integer until_date;
    private Boolean can_be_edited;
    private Boolean can_change_info;
    private Boolean can_post_messages;
    private Boolean can_edit_messages;
    private Boolean can_delete_messages;
    private Boolean can_invite_users;
    private Boolean can_restrict_members;
    private Boolean can_pin_messages;
    private Boolean can_promote_members;
    private Boolean can_send_messages;
    private Boolean can_send_media_messages;
    private Boolean can_send_other_messages;
    private Boolean can_add_web_page_previews;

}
