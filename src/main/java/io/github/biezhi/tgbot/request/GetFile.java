package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.GetFileResponse;

/**
 * stas
 * 5/1/16.
 */
public class GetFile extends BotRequest<GetFile, GetFileResponse> {

    public GetFile(String fileId) {
        super(GetFileResponse.class);
        add("file_id", fileId);
    }
}
