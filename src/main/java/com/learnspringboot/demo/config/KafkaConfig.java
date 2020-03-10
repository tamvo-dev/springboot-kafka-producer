package com.learnspringboot.demo.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author tamvo
 * @created 10/03/2020 - 8:33 AM
 */

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap.servers}")
    private String kafkaBootstrapServers;

    @Bean
    public Properties getProperties(){
        Properties producerProperties = new Properties();
        System.err.print(kafkaBootstrapServers);

        producerProperties.put("bootstrap.servers", kafkaBootstrapServers);
        producerProperties.put("acks", "all");
        producerProperties.put("retries", 0);
        producerProperties.put("batch.size", 16384);
        producerProperties.put("linger.ms", 1);
        producerProperties.put("buffer.memory", 33554432);
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return producerProperties;
    }

    @Bean
    public KafkaProducer<String, String> getKafkaProducer(){
        return new KafkaProducer<>(getProperties());
    }
}
