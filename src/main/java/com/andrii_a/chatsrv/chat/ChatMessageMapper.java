package com.andrii_a.chatsrv.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatMessageMapper {

    public ChatMessageDocument mapToDocument(ChatMessageDto chatMessageDto) {
        return ChatMessageDocument.builder()
                .id(chatMessageDto.id())
                .chatId(chatMessageDto.chatId())
                .senderId(chatMessageDto.senderId())
                .recipientId(chatMessageDto.recipientId())
                .content(chatMessageDto.content())
                .timestamp(chatMessageDto.timestamp())
                .build();
    }

    public ChatMessageDto mapToDto(ChatMessageDocument chatMessageDocument) {
        return new ChatMessageDto(
          chatMessageDocument.getId(),
                chatMessageDocument.getChatId(),
                chatMessageDocument.getSenderId(),
                chatMessageDocument.getRecipientId(),
                chatMessageDocument.getContent(),
                chatMessageDocument.getTimestamp()
        );
    }
}
