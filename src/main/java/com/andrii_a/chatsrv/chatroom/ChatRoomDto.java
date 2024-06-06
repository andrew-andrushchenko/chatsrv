package com.andrii_a.chatsrv.chatroom;

public record ChatRoomDto(
        String id,
        String chatId,
        String senderId,
        String recipientId
) {
}
