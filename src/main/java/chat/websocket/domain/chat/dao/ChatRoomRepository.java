package chat.websocket.domain.chat.dao;

import chat.websocket.domain.chat.entity.ChatRoom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("""
           SELECT cr
           FROM ChatRoom cr
           JOIN FETCH cr.messages m
           WHERE cr.id = :chatRoom
            """)
    Optional<ChatRoom> findChatRoomWithMessageById(@Param(value = "chatRoom") Long chatRoomId);


}
