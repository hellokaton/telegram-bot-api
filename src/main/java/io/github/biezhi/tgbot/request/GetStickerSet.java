package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.GetStickerSetResponse;

/**
 * Stas Parshin
 * 23 July 2017
 */
public class GetStickerSet extends BotRequest<GetStickerSet, GetStickerSetResponse> {

    public GetStickerSet(String name) {
        super(GetStickerSetResponse.class);
        add("name", name);
    }
}
