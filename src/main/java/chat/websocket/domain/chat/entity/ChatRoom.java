package chat.websocket.domain.chat.entity;

import chat.websocket.domain.member.entity.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    private String name;

    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> messages;

    @Builder
    public ChatRoom(Long id, String name, LocalDate createdAt, Member member,
            List<ChatMessage> messages) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.member = member;
        this.messages = messages != null ? messages : new ArrayList<>();
    }

    public static ChatRoom createRoom(Member member, String name) {
        return ChatRoom.builder()
                .name(name)
                .createdAt(LocalDate.now())
                .member(member)
                .build();
    }
}
