package chat.websocket.global.config.websocket;

import chat.websocket.domain.chat.dto.res.MessageGetDto;
import chat.websocket.domain.chat.entity.MessageType;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
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
        MessageGetDto joinMessage = MessageGetDto.createSystemMessage(principal.getName(),
                MessageType.JOIN);

        String destination = accessor.getDestination();
        Long roomId = extractRoomIdFromDestination(destination);

        messageTemplate.convertAndSend(CHAT_TOPIC_PREFIX + roomId, joinMessage);
    }

    @EventListener
    public void handleDisconnectEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        Principal principal = accessor.getUser();

        // 퇴장 메시지 생성
        MessageGetDto leaveMessage = MessageGetDto.createSystemMessage(principal.getName(),
                MessageType.LEAVE);

        String destination = accessor.getDestination();
        Long roomId = extractRoomIdFromDestination(destination);
        messageTemplate.convertAndSend(CHAT_TOPIC_PREFIX + roomId, leaveMessage);
    }




    private Long extractRoomIdFromDestination(String destination) {
        String[] parts = destination.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }

}
