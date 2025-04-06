package chat.websocket.global.auth.application;

import chat.websocket.domain.member.dao.MemberRepository;
import chat.websocket.domain.member.entity.Member;
import chat.websocket.global.auth.dto.RegisterDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void register(RegisterDto registerDto) {
        Optional<Member> member = memberRepository.findByAccount(registerDto.account());
        if (member.isPresent()) {
            throw new RuntimeException("이미 가입된 회원입니다.");
        }

        memberRepository.save(registerDto.toEntity(passwordEncoder));
    }

}
