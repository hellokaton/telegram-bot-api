package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

/**
 * Stas Parshin
 * 01 July 2017
 */
public class RestrictChatMember extends BotRequest<RestrictChatMember, BotResponse> {

    public RestrictChatMember(Object chatId, int userId) {
        super(BotResponse.class);
        add("chat_id", chatId).add("user_id", userId);
    }

    public RestrictChatMember untilDate(int untilDate) {
        return add("until_date", untilDate);
    }

    public RestrictChatMember canSendMessages(boolean canSendMessages) {
        return add("can_send_messages", canSendMessages);
    }

    public RestrictChatMember canSendMediaMessages(boolean canSendMediaMessages) {
        return add("can_send_media_messages", canSendMediaMessages);
    }

    public RestrictChatMember canSendOtherMessages(boolean canSendOtherMessages) {
        return add("can_send_other_messages", canSendOtherMessages);
    }

    public RestrictChatMember canAddWebPagePreviews(boolean canAddWebPagePreviews) {
        return add("can_add_web_page_previews", canAddWebPagePreviews);
    }
}
