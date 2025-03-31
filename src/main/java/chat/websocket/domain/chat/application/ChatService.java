package chat.websocket.domain.chat.application;

import chat.websocket.domain.chat.dao.ChatRepository;
import chat.websocket.domain.chat.dao.ChatRoomRepository;
import chat.websocket.domain.chat.dto.req.ChatAddDto;
import chat.websocket.domain.chat.dto.res.MessageGetDto;
import chat.websocket.domain.chat.entity.ChatMessage;
import chat.websocket.domain.chat.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {

    private static final String CHAT_TOPIC_PREFIX = "/topic/public/";

    private final SimpMessagingTemplate simpMessageTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;


    @Transactional
    public void sendMessage(ChatAddDto chatAddDto, String nickname) {
        // 채팅방 조회
        ChatRoom chatRoom = chatRoomRepository.findById(chatAddDto.chatRoomId()).orElseThrow(
                RuntimeException::new
        );

        // 새로운 채팅 생성 및 저장
        ChatMessage chatMessage = ChatMessage.of(chatRoom, chatAddDto, nickname);
        chatRepository.save(chatMessage);

        // 채팅방에 전송
        simpMessageTemplate.convertAndSend(CHAT_TOPIC_PREFIX + chatAddDto.chatRoomId(),
                MessageGetDto.from(chatMessage));
    }
}
