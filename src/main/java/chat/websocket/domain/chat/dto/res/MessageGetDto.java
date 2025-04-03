package chat.websocket.domain.chat.dto.res;

import chat.websocket.domain.chat.entity.ChatMessage;
import chat.websocket.domain.chat.entity.MessageType;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record MessageGetDto(
        Long id,
        String senderName,
        String content,
        MessageType messageType,
        LocalDateTime timeStamp
) {

    public static MessageGetDto from(ChatMessage chatMessage) {
        return MessageGetDto.builder()
                .id(chatMessage.getId())
                .senderName(chatMessage.getSenderName())
                .content(chatMessage.getContent())
                .messageType(chatMessage.getMessageType())
                .timeStamp(chatMessage.getTimeStamp())
                .build();
    }


}
