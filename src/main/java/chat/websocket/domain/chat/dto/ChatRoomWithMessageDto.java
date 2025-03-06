package chat.websocket.domain.chat.dto;

import chat.websocket.domain.chat.entity.ChatRoom;
import chat.websocket.domain.member.entity.Member;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatRoomWithMessageDto {

    private String name;
    private List<MessageDto> messages;

    public static ChatRoomWithMessageDto toDto(ChatRoom chatRoom) {
        List<MessageDto> messageList = chatRoom.getMessages().stream()
                .map(MessageDto::toDto)
                .toList();

        return ChatRoomWithMessageDto.builder()
                .name(chatRoom.getName())
                .messages(messageList)
                .build();
    }
}
