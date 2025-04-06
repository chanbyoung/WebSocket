package chat.websocket.global.auth.custom;

import chat.websocket.domain.member.dao.MemberRepository;
import chat.websocket.domain.member.entity.Member;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByAccount(username)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보 없음"));

        return CustomUser.builder()
                .username(member.getAccount())
                .nickname(member.getNickname())
                .authorities(Collections.singleton(
                        new SimpleGrantedAuthority("ROLE_USER")))
                .password(member.getPassword())
                .build();

    }
}
