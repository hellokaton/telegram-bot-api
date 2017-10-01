package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

import java.io.File;

/**
 * Stas Parshin
 * 01 July 2017
 */
public class SetChatPhoto extends BotRequest<SetChatPhoto, BotResponse> {

    public SetChatPhoto(Object chatId, byte[] photo) {
        super(BotResponse.class);
        add("chat_id", chatId).add("photo", photo);
    }

    public SetChatPhoto(Object chatId, File photo) {
        super(BotResponse.class);
        add("chat_id", chatId).add("photo", photo);
    }

    @Override
    public boolean isMultipart() {
        return true;
    }
}
