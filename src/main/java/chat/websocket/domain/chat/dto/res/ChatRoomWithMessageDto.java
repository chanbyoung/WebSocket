package chat.websocket.domain.chat.dto.res;

import chat.websocket.domain.chat.entity.ChatRoom;
import java.util.List;
import lombok.Builder;

@Builder
public record ChatRoomWithMessageDto(
        String name,
        List<MessageGetDto> messages
) {


    public static ChatRoomWithMessageDto toDto(ChatRoom chatRoom) {
        List<MessageGetDto> messageList = chatRoom.getMessages().stream()
                .map(MessageGetDto::from)
                .toList();

        return ChatRoomWithMessageDto.builder()
                .name(chatRoom.getName())
                .messages(messageList)
                .build();
    }
}
