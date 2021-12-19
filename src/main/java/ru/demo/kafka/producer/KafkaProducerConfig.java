package ru.demo.kafka.producer;

import org.apache.kafka.common.serialization.Serdes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import ru.demo.kafka.config.DefaultKafkaConfig;

@Configuration
public class KafkaProducerConfig extends DefaultKafkaConfig {

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


    public DefaultKafkaProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(getProps(), Serdes.String().serializer(), Serdes.String().serializer());
    }
}
