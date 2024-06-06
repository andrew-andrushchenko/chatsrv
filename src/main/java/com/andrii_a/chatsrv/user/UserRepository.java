package com.andrii_a.chatsrv.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserDocument, String> {
    List<UserDocument> findAllByStatus(UserStatus status);
}
