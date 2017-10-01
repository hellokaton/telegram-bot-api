package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderInfo implements Serializable {
    private final static long serialVersionUID = 0L;

    private String name, phone_number, email;
    private ShippingAddress shipping_address;

}
