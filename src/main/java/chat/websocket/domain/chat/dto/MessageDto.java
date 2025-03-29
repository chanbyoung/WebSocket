package chat.websocket.domain.chat.dto;

import chat.websocket.domain.chat.entity.ChatMessage;
import chat.websocket.domain.chat.entity.MessageType;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageDto {

    private String senderName;
    private String content;
    private MessageType messageType;
    private LocalDate timeStamp;


    public static MessageDto toDto(ChatMessage chatMessage) {
        return MessageDto.builder()
                .senderName(chatMessage.getMember().getNickname())
                .content(chatMessage.getContent())
                .messageType(chatMessage.getMessageType())
                .timeStamp(chatMessage.getTimeStamp())
                .build();
    }
}
