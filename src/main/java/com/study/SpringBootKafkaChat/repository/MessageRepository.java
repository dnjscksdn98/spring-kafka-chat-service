package com.study.SpringBootKafkaChat.repository;

import com.study.SpringBootKafkaChat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
