package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShippingQuery implements Serializable {
    private final static long serialVersionUID = 0L;

    private String          id;
    private User            from;
    private String          invoice_payload;
    private ShippingAddress shipping_address;

}
