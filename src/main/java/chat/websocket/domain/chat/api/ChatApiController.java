package chat.websocket.domain.chat.api;

import chat.websocket.domain.chat.application.ChatService;
import chat.websocket.domain.chat.dto.req.ChatAddDto;
import chat.websocket.global.auth.custom.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatApiController {

    private final ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    public ResponseEntity<Void> sendMessage(@RequestBody ChatAddDto chatAddDto, @AuthenticationPrincipal
            CustomUser customUser
            ) {
        chatService.sendMessage(chatAddDto, customUser.getNickname());
        return ResponseEntity.noContent().build();
    }

}
