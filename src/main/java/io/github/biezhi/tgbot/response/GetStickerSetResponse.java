package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.StickerSet;
import lombok.Data;

@Data
public class GetStickerSetResponse extends BotResponse {

    private StickerSet result;

}
