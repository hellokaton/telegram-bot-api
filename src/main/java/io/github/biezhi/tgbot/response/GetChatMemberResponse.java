package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.ChatMember;
import lombok.Data;

@Data
public class GetChatMemberResponse extends BotResponse {

    private ChatMember result;

}
