package com.study.SpringBootKafkaChat.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.SpringBootKafkaChat.constants.KafkaConstants;
import com.study.SpringBootKafkaChat.model.MessagePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageListener {

    @Autowired
    private SimpMessagingTemplate template;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    /**
     * 기능: 카프카 클러스터에 적재된 데이터를 자신이 처리해야될 토픽을 추출후 해당 채팅방으로 전달
     */
    @KafkaListener(topics = KafkaConstants.KAFKA_TOPIC, groupId = KafkaConstants.GROUP_ID)
    public void listen(MessagePayload message) throws Exception {
        LOGGER.info("message={}", message);

        Map<String, String> msg = new HashMap<>();
        msg.put("timestamp", message.getTimestamp());
        msg.put("message", message.getMessage());
        msg.put("user", message.getUser());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(msg);

        // TODO: 퍼블릭 토픽이 아니라 개인 채팅방 토픽으로 수정 - Ex) /topic/{chatRoomId}
        template.convertAndSend("/topic/public", json);
    }
}
