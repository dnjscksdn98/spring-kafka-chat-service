package com.study.SpringBootKafkaChat.controller;

import com.study.SpringBootKafkaChat.constants.KafkaConstants;
import com.study.SpringBootKafkaChat.model.ChatRoom;
import com.study.SpringBootKafkaChat.model.Message;
import com.study.SpringBootKafkaChat.model.MessagePayload;
import com.study.SpringBootKafkaChat.producer.MessageProducer;
import com.study.SpringBootKafkaChat.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ChatController {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    /**
     * 기능: URL /app/message 로 들어오는 메시지를 /topic/public 을 구독하고 있는 사람들에게 송신
     *
     * TODO: 퍼블릭이 아닌 개인 채팅방 토픽으로 변경
     */
    @MessageMapping("/message")
    public void sendMessage(MessagePayload message) throws Exception {
        messageProducer.send(KafkaConstants.KAFKA_TOPIC, message);
    }

    /**
     * 기능: 해당 토픽을 가진 채팅방의 기록들을 리스트로 반환
     *
     * TODO: 파라미터로 토픽 전달 - String topic
     */
    @GetMapping(path = "/history")
    public List<Message> getChattingHistory() {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findByName(KafkaConstants.KAFKA_TOPIC);
        return chatRoom.map(ChatRoom::getMessages).orElse(null);
    }
}
