package chat.websocket.domain.chat.dto.res;

import chat.websocket.domain.chat.entity.ChatRoom;
import lombok.Builder;

@Builder
public record ChatRoomWithMessageDto(
        String name,
        MessageListGetDto messages
) {


    public static ChatRoomWithMessageDto of(ChatRoom chatRoom, MessageListGetDto messageList) {
        return ChatRoomWithMessageDto.builder()
                .name(chatRoom.getName())
                .messages(messageList)
                .build();
    }
}
