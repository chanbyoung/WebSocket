package chat.websocket.global.auth.api;

import chat.websocket.global.auth.application.AuthService;
import chat.websocket.global.auth.dto.LoginDto;
import chat.websocket.global.auth.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("member", new RegisterDto());
        return "member/register";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("member", new LoginDto());
        return "member/login";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute RegisterDto registerDto) {
        log.info("{}",registerDto);
        authService.register(registerDto);
        return "redirect:/";
    }


}
