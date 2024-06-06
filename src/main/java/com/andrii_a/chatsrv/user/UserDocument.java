package com.andrii_a.chatsrv.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class UserDocument {
    @Id
    private String nickName;
    private String fullName;
    private UserStatus status;
}
