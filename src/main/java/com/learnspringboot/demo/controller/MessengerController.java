package com.learnspringboot.demo.controller;

import com.sun.istack.internal.logging.Logger;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tamvo
 * @created 10/03/2020 - 8:37 AM
 */

@Controller
public class MessengerController {

    @Autowired
    private KafkaProducer<String, String> kafkaProducer;

    private static final String TOPIC = "test";
    private static final Logger logger = Logger.getLogger(MessengerController.class);

    @RequestMapping("api/messenger/{messenger}")
    public ResponseEntity<String> getMessenger(@PathVariable("messenger") String messenger){
        kafkaProducer.send(new ProducerRecord<>(TOPIC, messenger));
        logger.info("Send messenger: " + messenger);
        return ResponseEntity.ok("Success");
    }

}
