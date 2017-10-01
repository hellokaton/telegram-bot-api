package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Invoice implements Serializable {
    private final static long serialVersionUID = 0L;

    private String  title;
    private String  description;
    private String  start_parameter;
    private String  currency;
    private Integer total_amount;

}
