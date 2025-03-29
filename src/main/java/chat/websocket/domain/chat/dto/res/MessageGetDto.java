package chat.websocket.domain.chat.dto.res;

import chat.websocket.domain.chat.entity.ChatMessage;
import chat.websocket.domain.chat.entity.MessageType;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageGetDto {

    private String senderName;
    private String content;
    private MessageType messageType;
    private LocalDate timeStamp;


    public static MessageGetDto toDto(ChatMessage chatMessage) {
        return MessageGetDto.builder()
                .senderName(chatMessage.getSenderName())
                .content(chatMessage.getContent())
                .messageType(chatMessage.getMessageType())
                .timeStamp(chatMessage.getTimeStamp())
                .build();
    }
}
