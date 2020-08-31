package com.study.SpringBootKafkaChat.producer;

import com.study.SpringBootKafkaChat.model.ChatRoom;
import com.study.SpringBootKafkaChat.model.Message;
import com.study.SpringBootKafkaChat.model.MessagePayload;
import com.study.SpringBootKafkaChat.repository.ChatRoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class MessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    private final KafkaTemplate<String, MessagePayload> kafkaTemplate;

    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public MessageProducer(KafkaTemplate<String, MessagePayload> kafkaTemplate, ChatRoomRepository chatRoomRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.chatRoomRepository = chatRoomRepository;
    }

    /**
     * 기능: 프로듀서(Producer)가 카프카 클러스터(Kafka Cluster)로 적재
     */
    @Transactional
    public void send(String topic, MessagePayload message) {
        LOGGER.info(String.format("Sending Message = {%s} to Topic = {%s}", message, topic));

        // TODO: 카프카 토픽이 아닌 개인 채팅방 토픽으로 수정
        ChatRoom chatRoom = chatRoomRepository
                .findByName(topic)
                .orElse(new ChatRoom(topic));

        Message newMessage = new Message(message.getUser(), message.getMessage());
        newMessage.setTimestamp(LocalDateTime.now().toString());
        chatRoom.addMessage(newMessage);
        chatRoomRepository.save(chatRoom);

        kafkaTemplate.send(topic, message);
    }
}
