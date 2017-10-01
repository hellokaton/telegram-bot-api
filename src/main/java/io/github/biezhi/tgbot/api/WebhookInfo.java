package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class WebhookInfo implements Serializable {

    private final static long serialVersionUID = 0L;

    private String   url;
    private Boolean  has_custom_certificate;
    private Integer  pending_update_count;
    private Integer  last_error_date;
    private String   last_error_message;
    private Integer  max_connections;
    private String[] allowed_updates;

}
