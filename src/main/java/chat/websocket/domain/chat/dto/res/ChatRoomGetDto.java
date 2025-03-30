package chat.websocket.domain.chat.dto.res;

import chat.websocket.domain.chat.entity.ChatRoom;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record ChatRoomGetDto(
        Long id,
        String name,
        LocalDate createdAt
) {

    public static ChatRoomGetDto toDto(ChatRoom chatRoom) {
        return ChatRoomGetDto.builder()
                .id(chatRoom.getId())
                .name(chatRoom.getName())
                .createdAt(chatRoom.getCreatedAt())
                .build();

    }

}
