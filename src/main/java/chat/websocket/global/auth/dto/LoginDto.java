package chat.websocket.global.auth.dto;


public record LoginDto(
        String account,
        String password
) {

    public LoginDto() {
        this("", "");
    }
}


