package ru.demo.kafka.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void produceQuickstart(String value) {
        UUID uuid = UUID.randomUUID();
        log.info("producer>> - producing message {} ", uuid);
        kafkaTemplate.send("quickstart-events", uuid.toString(), value).addCallback(result -> {
            log.info("onSuccess - message delivered");
        }, error -> {
            log.error("onError - error {}", error.getMessage());
            throw new RuntimeException(error);
        });
    }

    public void produceStatus(String messageId, String status) {
        var status1 = Status.builder().status(status).messageId(messageId).build();
        kafkaTemplate.send("events-status", status1.toString()).addCallback(result -> {
            log.info("onSuccess - message delivered to status topic");
        }, error -> {
            log.error("onError - error {}", error.getMessage());
            throw new RuntimeException(error);
        });
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Status {
        private String messageId;
        private String status;
    }
}
