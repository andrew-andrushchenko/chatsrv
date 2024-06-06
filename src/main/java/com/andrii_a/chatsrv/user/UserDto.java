package com.andrii_a.chatsrv.user;

public record UserDto(
        String nickname,
        String fullName,
        UserStatus status
) {
}
