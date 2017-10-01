package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 23 July 2017
 */
public class SetStickerPositionInSet extends BotRequest<SetStickerPositionInSet, BotResponse> {

    public SetStickerPositionInSet(String sticker, int position) {
        super(BotResponse.class);
        add("sticker", sticker).add("position", position);
    }
}
