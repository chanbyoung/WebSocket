package chat.websocket.global.config.websocket;

import chat.websocket.domain.chat.dto.res.MessageGetDto;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private static final String CHAT_TOPIC_PREFIX = "/topic/public/";

    private final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleSubscribeEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        Principal principal = accessor.getUser();
        MessageGetDto joinMessage = MessageGetDto.createJoinMessage(principal.getName());

        String destination = accessor.getDestination();
        Long roomId = extractRoomIdFromDestination(destination);

        messageTemplate.convertAndSend(CHAT_TOPIC_PREFIX + roomId, joinMessage);
    }


    private Long extractRoomIdFromDestination(String destination) {
        String[] parts = destination.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }

}
