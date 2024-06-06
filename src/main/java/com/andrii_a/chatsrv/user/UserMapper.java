package com.andrii_a.chatsrv.user;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDocument mapToDocument(UserDto other) {
        UserDocument document = new UserDocument();
        document.setNickName(other.nickname());
        document.setFullName(other.fullName());
        document.setStatus(other.status());
        return document;
    }

    public UserDto mapToDto(UserDocument other) {
        return new UserDto(
                other.getNickName(),
                other.getNickName(),
                other.getStatus()
        );
    }
}
