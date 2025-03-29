package chat.websocket.domain.chat.dto.res;

import chat.websocket.domain.chat.entity.ChatRoom;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatRoomGetDto {
    private Long id;
    private String name;
    private LocalDate createdAt;

    public static ChatRoomGetDto toDto(ChatRoom chatRoom) {
        return ChatRoomGetDto.builder()
                .id(chatRoom.getId())
                .name(chatRoom.getName())
                .createdAt(chatRoom.getCreatedAt())
                .build();

    }

}
