package chat.websocket.global.auth.custom;

import java.util.Collection;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Builder
public class CustomUser implements UserDetails {
    private String username;
    private String password;
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;

}
