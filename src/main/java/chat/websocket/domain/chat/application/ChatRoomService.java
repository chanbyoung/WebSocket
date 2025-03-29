package chat.websocket.domain.chat.application;

import chat.websocket.domain.chat.dao.ChatRoomRepository;
import chat.websocket.domain.chat.dto.res.ChatRoomGetDto;
import chat.websocket.domain.chat.dto.res.ChatRoomWithMessageDto;
import chat.websocket.domain.chat.entity.ChatRoom;
import chat.websocket.domain.member.dao.MemberRepository;
import chat.websocket.domain.member.entity.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
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
        ChatRoom chatRoom = chatRoomRepository.findChatRoomWithMessageById(chatRoomId)
                .orElseThrow(RuntimeException::new);

        return ChatRoomWithMessageDto.toDto(chatRoom);
    }

}
