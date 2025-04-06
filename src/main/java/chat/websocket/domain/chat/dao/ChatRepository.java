package chat.websocket.domain.chat.dao;

import chat.websocket.domain.chat.entity.ChatMessage;
import java.time.LocalDateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    @Query("""
           SELECT m
           FROM ChatMessage m
           WHERE m.chatRoom.id = :chatRoomId
           AND ((m.timeStamp < :lastTimeStamp)
           OR (m.timeStamp = :lastTimeStamp AND m.id < :lastId))
           ORDER BY m.timeStamp DESC, m.id DESC
           """)
    Slice<ChatMessage> findChatByCursor(
            @Param("chatRoomId") Long chatRomId,
            @Param("lastTimeStamp") LocalDateTime lastTimestamp,
            @Param("lastId") Long lastId,
            Pageable pageable
    );

}
