package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShippingAddress implements Serializable {
    private final static long serialVersionUID = 0L;

    private String country_code;
    private String state;
    private String city;
    private String street_line1;
    private String street_line2;
    private String post_code;

}
