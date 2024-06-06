package com.andrii_a.chatsrv.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageDto chatMessageDto) {
        ChatMessageDto savedMsg = chatMessageService.save(chatMessageDto);
        messagingTemplate.convertAndSendToUser(
                chatMessageDto.recipientId(), "/queue/messages",
                new ChatNotification(
                        savedMsg.id(),
                        savedMsg.senderId(),
                        savedMsg.recipientId(),
                        savedMsg.content()
                )
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessageDto>> findChatMessages(@PathVariable String senderId,
                                                                 @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
}
