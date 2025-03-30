package chat.websocket.global.auth.dto;

import chat.websocket.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;

public record RegisterDto(
        @NotNull
        String account,
        @NotNull
        String password,
        @NotNull
        String nickname
){

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .account(this.account)
                .password(passwordEncoder.encode(this.password))
                .nickname(this.nickname)
                .build();
    }

    public RegisterDto() {
        this("", "", "");
    }

}
