package com.andrii_a.chatsrv.chat;

import java.util.Date;

public record ChatMessageDto(
        String id,
        String chatId,
        String senderId,
        String recipientId,
        String content,
        Date timestamp
) {
}
