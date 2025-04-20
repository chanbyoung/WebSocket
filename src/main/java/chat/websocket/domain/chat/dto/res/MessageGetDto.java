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


    public static MessageGetDto createSystemMessage(String name, MessageType type) {
        String content = switch (type) {
            case JOIN -> name + "님이 입장하셨습니다.";
            case LEAVE -> name + "님이 퇴장하셨습니다.";
            default -> throw new IllegalArgumentException("지원하지 않는 메시지 타입: " + type);
        };

        return MessageGetDto.builder()
                .messageType(type)
                .senderName("SYSTEM")
                .content(content)
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
