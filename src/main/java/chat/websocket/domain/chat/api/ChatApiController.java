package chat.websocket.domain.chat.api;

import chat.websocket.domain.chat.application.ChatService;
import chat.websocket.domain.chat.dto.req.ChatAddDto;
import chat.websocket.global.auth.custom.CustomUser;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatApiController {

    private final ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    public ResponseEntity<Void> sendMessage(ChatAddDto chatAddDto,
            Authentication authentication
            ) {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUser customUser) {
            chatService.sendMessage(chatAddDto, customUser.getNickname());
        } else {
            throw new IllegalStateException("인증된 사용자 정보가 올바르지 않습니다.");
        }
        return ResponseEntity.noContent().build();
    }

}
