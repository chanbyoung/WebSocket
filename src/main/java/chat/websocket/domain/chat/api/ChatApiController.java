package chat.websocket.domain.chat.api;

import chat.websocket.domain.chat.application.ChatService;
import chat.websocket.domain.chat.dto.req.ChatAddDto;
import chat.websocket.global.auth.custom.CustomUser;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatApiController {

    private final ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    public ResponseEntity<Void> sendMessage(@RequestBody ChatAddDto chatAddDto,
            Principal principal
            ) {
        log.info("senderId ={} ", principal.getName());
        chatService.sendMessage(chatAddDto, principal.getName());
        return ResponseEntity.noContent().build();
    }

}
