package chat.websocket.global.config.websocket;

import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Component
@Slf4j
public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    private static final String SECURITY_CONTEXT ="SPRING_SECURITY_CONTEXT";

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
            Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest servletRequest) {
            HttpSession session = servletRequest.getServletRequest().getSession(false);

            if (session != null) {
                SecurityContext securityContext = (SecurityContext) session.getAttribute(
                        SECURITY_CONTEXT);
                if (securityContext != null) {
                    Authentication authentication = securityContext.getAuthentication();
                    if (authentication != null && authentication.isAuthenticated()) {
                        log.info("HandshakeHandler.authenticaton = {} ", authentication);
                        return authentication;
                    }
                }
            }
        }

        return null;
    }

}
