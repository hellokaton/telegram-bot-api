package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.GetGameHighScoresResponse;

/**
 * Stas Parshin
 * 04 October 2016
 */
public class GetGameHighScores extends BotRequest<GetGameHighScores, GetGameHighScoresResponse> {

    public GetGameHighScores(int userId, Object chatId, int messageId) {
        super(GetGameHighScoresResponse.class);
        add("user_id", userId).add("chat_id", chatId).add("message_id", messageId);
    }

    public GetGameHighScores(int userId, String inlineMessageId) {
        super(GetGameHighScoresResponse.class);
        add("user_id", userId).add("inline_message_id", inlineMessageId);
    }

}
