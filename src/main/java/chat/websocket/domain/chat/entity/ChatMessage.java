package chat.websocket.domain.chat.entity;

import chat.websocket.domain.chat.dto.req.ChatAddDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(
        name = "chat_message",
        indexes = {
                @Index(name = "idx_chat_room_time_id", columnList = "chat_room_id, time_stamp, chat_message_id")
        }
)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private Long id;

    private String content;

    private String senderName;

    private LocalDateTime timeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    public static ChatMessage of(ChatRoom chatRoom, ChatAddDto chatAddDto, String senderName) {
        return ChatMessage.builder()
                .content(chatAddDto.content())
                .senderName(senderName)
                .timeStamp(LocalDateTime.now())
                .chatRoom(chatRoom)
                .messageType(chatAddDto.messageType())
                .build();
    }


}
