package io.github.biezhi.tgbot.api.request;

import java.io.Serializable;

public class InlineKeyboardMarkup extends Keyboard implements Serializable {
    private final static long serialVersionUID = 0L;

    private final InlineKeyboardButton[][] inline_keyboard;

    public InlineKeyboardMarkup(InlineKeyboardButton[]... keyboard) {
        this.inline_keyboard = keyboard;
    }
}