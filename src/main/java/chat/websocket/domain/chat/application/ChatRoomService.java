package chat.websocket.domain.chat.application;

import chat.websocket.domain.chat.dao.ChatRepository;
import chat.websocket.domain.chat.dao.ChatRoomRepository;
import chat.websocket.domain.chat.dto.res.ChatRoomGetDto;
import chat.websocket.domain.chat.dto.res.ChatRoomWithMessageDto;
import chat.websocket.domain.chat.dto.res.MessageGetDto;
import chat.websocket.domain.chat.dto.res.MessageListGetDto;
import chat.websocket.domain.chat.entity.ChatMessage;
import chat.websocket.domain.chat.entity.ChatRoom;
import chat.websocket.domain.member.dao.MemberRepository;
import chat.websocket.domain.member.entity.Member;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ChatRoom addRoom(String account, String name) {
        Member member = memberRepository.findByAccount(account)
                .orElseThrow(RuntimeException::new);

        ChatRoom chatRoom = ChatRoom.createRoom(member, name);

        return chatRoomRepository.save(chatRoom);

    }

    // 채팅방 전체 조회
    public List<ChatRoomGetDto> getRoomList() {
        return chatRoomRepository.findAll().stream()
                .map(ChatRoomGetDto::toDto)
                .toList();
    }

    // 채팅방 조회
    public ChatRoomWithMessageDto getChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(RuntimeException::new);

        // 기본 커서 값
        LocalDateTime defaultTimeStamp = LocalDateTime.now();
        Long defaultId = Long.MAX_VALUE;

        // 메시지 슬라이스 조회
        MessageListGetDto chatRoomRoad = getChatRoomRoad(chatRoomId, defaultTimeStamp, defaultId);
        return ChatRoomWithMessageDto.of(chatRoom, chatRoomRoad);
    }

    public MessageListGetDto getChatRoomRoad(Long roomId, LocalDateTime lastTimeStamp, Long lastId) {
        Pageable pageable = PageRequest.of(0, 20); // limit = 20
        Slice<ChatMessage> messageSlice = chatRepository.findChatByCursor(
                roomId, lastTimeStamp, lastId, pageable);

        log.info("hasNext = {} ", messageSlice.hasNext());
        List<MessageGetDto> messages = messageSlice.getContent()
                .stream()
                .map(MessageGetDto::from)
                .toList();

        return MessageListGetDto.of(messages, messageSlice.hasNext());
    }
}
