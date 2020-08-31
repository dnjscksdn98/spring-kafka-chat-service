package com.study.SpringBootKafkaChat;

import com.study.SpringBootKafkaChat.model.ChatRoom;
import com.study.SpringBootKafkaChat.model.Message;
import com.study.SpringBootKafkaChat.repository.ChatRoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringBootKafkaChatApplication implements CommandLineRunner {

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootKafkaChatApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaChatApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		chatRoomRepository.deleteAll();
//
//		final List<Message> newMessages = List.of(
//				new Message("Alex", "Hello World", LocalDateTime.now().toString()),
//				new Message("Park", "Bye Bye", LocalDateTime.now().toString())
//		);
//
//		final ChatRoom chatRoom = new ChatRoom("Kafka Chat", newMessages);
//		chatRoomRepository.save(chatRoom);
//
//		List<Message> messages = chatRoomRepository.findByName("Kafka Chat").get().getMessages();
//		messages.forEach(message -> LOGGER.info("Message List -> {}", message));
	}
}
