package com.andrii_a.chatsrv.chat;

import com.andrii_a.chatsrv.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;
    private final ChatMessageMapper chatMessageMapper;

    public ChatMessageDto save(ChatMessageDto chatMessage) {
        var chatId = chatRoomService
                .getChatRoomId(chatMessage.senderId(), chatMessage.recipientId(), true)
                .orElseThrow(); // You can create your own dedicated exception
        ChatMessageDocument chatMessageDocument = chatMessageMapper.mapToDocument(chatMessage);
        chatMessageDocument.setChatId(chatId);
        chatMessageRepository.save(chatMessageDocument);
        return chatMessage;
    }

    public List<ChatMessageDto> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(id -> {
            List<ChatMessageDocument> chatRoomDocumentList = chatMessageRepository.findByChatId(id);
            return chatRoomDocumentList.stream().map(chatMessageMapper::mapToDto).collect(Collectors.toList());
        }).orElse(new ArrayList<>());
    }
}
