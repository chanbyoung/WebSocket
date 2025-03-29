package chat.websocket.domain.chat.dto.req;

import chat.websocket.domain.chat.entity.MessageType;

public record ChatAddDto(
        Long chatRoomId,
        String content,
        MessageType messageType
) {

}
