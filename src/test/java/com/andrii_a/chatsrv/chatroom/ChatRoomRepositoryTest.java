package com.andrii_a.chatsrv.chatroom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
class ChatRoomRepositoryTest {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @BeforeEach
    public void setup() {
        chatRoomRepository.deleteAll();
    }

    @Test
    public void should_successfully_create_a_chat_room() {
        String senderId = "jack";
        String recipientId = "alex";
        String chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        chatRoomRepository.save(senderRecipient);

        assertThat(chatRoomRepository.findAll().size()).isNotEqualTo(0);
    }

    @Test
    public void should_successfully_find_a_chat_room_by_sender_and_recipient_id() {
        String senderId = "jack";
        String recipientId = "alex";
        String chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom recipientSender = ChatRoom
                .builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        Optional<ChatRoom> senderRecipientOptional = chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        assertThat(senderRecipientOptional.isPresent()).isTrue();
        assertThat(senderRecipientOptional.get()).isEqualTo(senderRecipient);

        Optional<ChatRoom> recipientSenderOptional = chatRoomRepository.findBySenderIdAndRecipientId(recipientId, senderId);
        assertThat(recipientSenderOptional.isPresent()).isTrue();
        assertThat(recipientSenderOptional.get()).isEqualTo(recipientSender);

        assertThat(senderRecipientOptional.get().getChatId()).isEqualTo(recipientSenderOptional.get().getChatId());
    }
}