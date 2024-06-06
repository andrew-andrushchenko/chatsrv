package com.andrii_a.chatsrv.user;

public record UserDto(
        String nickName,
        String fullName,
        UserStatus status
) {
}
