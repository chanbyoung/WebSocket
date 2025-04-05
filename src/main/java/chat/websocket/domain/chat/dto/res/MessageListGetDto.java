package chat.websocket.domain.chat.dto.res;

import java.util.List;
import lombok.Builder;

@Builder
public record MessageListGetDto(
        List<MessageGetDto> messages,
        boolean hasNext
) {

    public static MessageListGetDto of(List<MessageGetDto> message, boolean hasNext) {
        return MessageListGetDto.builder()
                .messages(message)
                .hasNext(hasNext)
                .build();
    }
}
