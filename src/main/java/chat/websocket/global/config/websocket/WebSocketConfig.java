package chat.websocket.global.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket 설정 클래스
 *
 * - STOMP 프로토콜을 지원하는 WebSocket을 활성화
 * - 메시지 브로커를 설정하여 클라이언트 간 실시간 메시지 통신 가능
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 클라이언트(Web, 모바일 등)가 연결할 WebSocket 엔드포인트 설정
     *
     * - `/ws` 엔드포인트를 통해 WebSocket 연결 가능
     * - SockJS를 지원하여 WebSocket 미지원 환경에서도 폴백(fallback) 메커니즘 사용 가능
     *
     * @param registry WebSocket 엔드포인트 등록을 위한 객체
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 WebSocket 연결을 시작할 때 사용할 엔드포인트 정의
        registry.addEndpoint("/ws")  // WebSocket 연결 엔드포인트를 `/ws`로 설정
                .withSockJS();       // WebSocket을 지원하지 않는 환경에서 SockJS 사용 가능
    }

    /**
     * 메시지 브로커 설정 (서버 → 클라이언트 메시지 전달 방식 정의)
     *
     * - `/topic` 경로를 구독한 클라이언트에게 메시지 브로드캐스트 가능
     * - `/app` 경로로 들어오는 메시지를 처리하도록 설정
     *
     * @param config 메시지 브로커 설정 객체
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 서버에서 클라이언트로 메시지를 전달할 때 사용하는 **메시지 브로커 설정**
        // -> 클라이언트가 특정 주제를 구독하면 해당 메시지를 받을 수 있음
        config.enableSimpleBroker("/topic");  // 메시지 구독 prefix (ex: /topic/public)

        // 클라이언트에서 서버로 메시지를 보낼 때 사용하는 prefix 설정
        // 예: 클라이언트가 "/app/chat.sendMessage"로 메시지를 전송하면
        //     서버의 @MessageMapping("/chat.sendMessage")에서 처리됨
        config.setApplicationDestinationPrefixes("/app");
    }
}