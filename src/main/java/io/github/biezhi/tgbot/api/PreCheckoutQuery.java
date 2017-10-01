package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class PreCheckoutQuery implements Serializable {
    private final static long serialVersionUID = 0L;

    private String    id;
    private User      from;
    private String    currency;
    private Integer   total_amount;
    private String    invoice_payload;
    private String    shipping_option_id;
    private OrderInfo order_info;

}
