package com.andrii_a.chatsrv.chatroom;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoomDocument, String> {
    Optional<ChatRoomDocument> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
