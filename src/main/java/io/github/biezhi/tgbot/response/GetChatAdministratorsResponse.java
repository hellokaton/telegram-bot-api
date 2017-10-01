package io.github.biezhi.tgbot.response;

import io.github.biezhi.tgbot.api.ChatMember;
import lombok.Data;

import java.util.List;

@Data
public class GetChatAdministratorsResponse extends BotResponse {

    private List<ChatMember> result;

}
