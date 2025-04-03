package chat.websocket.domain.chat.dto.res;

import chat.websocket.domain.chat.entity.ChatRoom;
import java.util.List;
import lombok.Builder;

@Builder
public record ChatRoomWithMessageDto(
        String name,
        MessageListGetDto messages
) {


    public static ChatRoomWithMessageDto toDto(ChatRoom chatRoom, List<MessageGetDto> messageList) {
        return ChatRoomWithMessageDto.builder()
                .name(chatRoom.getName())
                .messages(MessageListGetDto.from(messageList))
                .build();
    }
}
