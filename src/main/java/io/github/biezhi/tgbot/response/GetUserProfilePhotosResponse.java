package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.UserProfilePhotos;
import lombok.Data;

@Data
public class GetUserProfilePhotosResponse extends BotResponse {

    private UserProfilePhotos result;

}
