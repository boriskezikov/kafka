package ru.demo.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.demo.kafka.producer.Producer;

@Slf4j
@Service
@RequiredArgsConstructor
public class Consumer {

    private final Producer producer;

    @KafkaListener(topics = "quickstart-events", containerFactory = "kafkaListenerContainerFactory")
    public void listenTopic1(@Payload ConsumerRecord<String, String> consumerRecord) {
        log.info("consumer>> - reading message {}", consumerRecord.value());
        producer.produceStatus(consumerRecord.key(), "PROCESSED");
    }
}
