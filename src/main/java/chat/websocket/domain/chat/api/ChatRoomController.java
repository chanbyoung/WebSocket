package chat.websocket.domain.chat.api;

import chat.websocket.domain.chat.application.ChatRoomService;
import chat.websocket.domain.chat.dto.res.ChatRoomGetDto;
import chat.websocket.domain.chat.dto.res.ChatRoomWithMessageDto;
import chat.websocket.domain.chat.entity.ChatRoom;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/chat")
@Slf4j
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // 채팅방 생성 폼
    @GetMapping("/rooms/new")
    public String createRoomForm() {
        return "chat/createRoom";
    }

    // 채팅방 전체 조회
    @GetMapping("/rooms")
    public String getRooms(Model model) {
        List<ChatRoomGetDto> roomList = chatRoomService.getRoomList();
        model.addAttribute("roomList", roomList);
        return "chat/rooms";
    }

    // 채팅방 생성
    @PostMapping("/rooms/new")
    public String createRoom(@RequestParam(name = "name") String name, @AuthenticationPrincipal
            UserDetails user) {
        log.info("name = {} ", name);
        ChatRoom chatRoom = chatRoomService.addRoom(user.getUsername(), name);
        return "redirect:/chat/rooms/" + chatRoom.getId();
    }

    // 특정 채팅방 입장
    @GetMapping("/room/{roomId}")
    public String enterRoom(@PathVariable Long roomId, Model model) {
        ChatRoomWithMessageDto chatRoom = chatRoomService.getChatRoom(roomId);
        model.addAttribute("chatRoom", chatRoom);
        return "chat/chatRoom";
    }


}
