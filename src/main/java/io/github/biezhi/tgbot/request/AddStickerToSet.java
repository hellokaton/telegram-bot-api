package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 23 July 2017
 */
public class AddStickerToSet extends AbstractUploadRequest<AddStickerToSet, BotResponse> {

    public AddStickerToSet(Integer userId, String name, Object pngSticker, String emojis) {
        super(BotResponse.class, "png_sticker", pngSticker);
        add("user_id", userId);
        add("name", name);
        add("emojis", emojis);
    }
}
