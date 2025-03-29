package chat.websocket.domain.chat.api;

import chat.websocket.domain.chat.application.ChatRoomService;
import chat.websocket.domain.chat.dto.res.ChatRoomGetDto;
import chat.websocket.domain.chat.dto.res.ChatRoomWithMessageDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/chatRoom")
@RequiredArgsConstructor
public class ChatRoomApiController {

    private final ChatRoomService chatRoomService;

    @GetMapping
    public ResponseEntity<List<ChatRoomGetDto>> getRooms() {
        List<ChatRoomGetDto> roomList = chatRoomService.getRoomList();
        return ResponseEntity.ok(roomList);
    }

    @PostMapping
    public ResponseEntity<Void> addRoom(@RequestBody String name, @AuthenticationPrincipal
            UserDetails user) {
        chatRoomService.addRoom(user.getUsername(), name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoomWithMessageDto> getRoom(@PathVariable Long roomId) {
        ChatRoomWithMessageDto chatRoom = chatRoomService.getChatRoom(roomId);
        return ResponseEntity.ok(chatRoom);
    }



}
