package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.api.MaskPosition;
import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 23 July 2017
 */
public class CreateNewStickerSet extends AbstractUploadRequest<CreateNewStickerSet, BotResponse> {

    public CreateNewStickerSet(Integer userId, String name, String title, Object pngSticker, String emojis) {
        super(BotResponse.class, "png_sticker", pngSticker);
        add("user_id", userId);
        add("name", name);
        add("title", title);
        add("emojis", emojis);
    }

    public CreateNewStickerSet containsMasks(boolean containsMasks) {
        return add("contains_masks", containsMasks);
    }

    public CreateNewStickerSet maskPosition(MaskPosition maskPosition) {
        return add("mask_position", serialize(maskPosition)).containsMasks(true);
    }
}
