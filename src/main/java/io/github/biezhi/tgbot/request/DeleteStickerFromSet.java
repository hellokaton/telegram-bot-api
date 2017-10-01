package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 23 July 2017
 */
public class DeleteStickerFromSet extends BotRequest<DeleteStickerFromSet, BotResponse> {
    public DeleteStickerFromSet(String sticker) {
        super(BotResponse.class);
        add("sticker", sticker);
    }
}
