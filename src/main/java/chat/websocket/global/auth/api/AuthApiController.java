package chat.websocket.global.auth.api;

import chat.websocket.global.auth.application.AuthService;
import chat.websocket.global.auth.custom.CustomUser;
import chat.websocket.global.auth.dto.LoginDto;
import chat.websocket.global.auth.dto.RegisterDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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
