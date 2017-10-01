package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class SuccessfulPayment implements Serializable {
    private final static long serialVersionUID = 0L;

    private String    currency;
    private Integer   total_amount;
    private String    invoice_payload;
    private String    shipping_option_id;
    private OrderInfo order_info;
    private String    telegram_payment_charge_id;
    private String    provider_payment_charge_id;

}
