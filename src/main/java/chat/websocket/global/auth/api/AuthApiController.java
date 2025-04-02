package chat.websocket.global.auth.api;

import chat.websocket.global.auth.application.AuthService;
import chat.websocket.global.auth.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Validated @RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        return ResponseEntity.noContent().build();
    }


}
