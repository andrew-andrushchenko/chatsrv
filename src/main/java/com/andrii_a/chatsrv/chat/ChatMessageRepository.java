package com.andrii_a.chatsrv.chat;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessageDocument, String> {
    List<ChatMessageDocument> findByChatId(String chatId);
}
