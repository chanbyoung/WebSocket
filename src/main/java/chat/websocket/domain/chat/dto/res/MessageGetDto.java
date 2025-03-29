package chat.websocket.domain.chat.dto.res;

import chat.websocket.domain.chat.entity.ChatMessage;
import chat.websocket.domain.chat.entity.MessageType;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Builder
public record MessageGetDto(
        String senderName,
        String content,
        MessageType messageType,
        LocalDate timeStamp
) {

    public static MessageGetDto toDto(ChatMessage chatMessage) {
        return MessageGetDto.builder()
                .senderName(chatMessage.getSenderName())
                .content(chatMessage.getContent())
                .messageType(chatMessage.getMessageType())
                .timeStamp(chatMessage.getTimeStamp())
                .build();
    }
}
