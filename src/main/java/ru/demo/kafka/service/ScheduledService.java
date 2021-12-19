package ru.demo.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.demo.kafka.producer.Producer;

import java.util.Random;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledService {

    private final Producer producer;
    private final RandomStringGenerator randomStringGenerator;


    @Scheduled(fixedDelay = 5000)
    public void generateAndProduce() {
        log.info("[JOB] - =======================================================");
        log.info("[JOB] - generateAndProduce started");
        Random random = new Random();
        Stream.generate(() -> randomStringGenerator.generate(30))
                .limit(random.nextInt(100))
                .forEach(producer::produceQuickstart);
        log.info("[JOB] - generateAndProduce finished");
        log.info("[JOB] - =======================================================");
    }
}
