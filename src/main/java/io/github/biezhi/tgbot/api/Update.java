package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Update implements Serializable {
    private final static long serialVersionUID = 0L;

    private Integer            update_id;
    private Message            message;
    private Message            edited_message;
    private Message            channel_post;
    private Message            edited_channel_post;
    private InlineQuery        inline_query;
    private ChosenInlineResult chosen_inline_result;
    private CallbackQuery      callback_query;
    private ShippingQuery      shipping_query;
    private PreCheckoutQuery   pre_checkout_query;

}
