package chat.websocket.domain.chat.dto.res;

import java.util.List;
import lombok.Builder;

@Builder
public record MessageListGetDto(
        List<MessageGetDto> message
) {

    public static MessageListGetDto from(List<MessageGetDto> message) {
        return MessageListGetDto.builder()
                .message(message)
                .build();
    }
}
