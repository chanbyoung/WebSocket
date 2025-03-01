package chat.websocket.global.auth.dto;

import chat.websocket.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class RegisterDto{

    @NotNull
    private String account;
    @NotNull
    private String password;
    @NotNull
    private String nickname;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .account(this.account)
                .password(passwordEncoder.encode(this.password))
                .nickname(this.nickname)
                .build();
    }

}
