package com.sample.kafka.service;

import com.sample.kafka.dto.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Log4j2
//@Service
public class KafkaSubscriber {

    private CountDownLatch latch = new CountDownLatch(1);

//    @KafkaListener(topics = "sample.topic")
//    public void getMessage(SampleDTO dto) {
//        log.info("received message='{}'", dto);
//        latch.countDown();
//    }

//    @KafkaListener(topics = "sample.topic")
//    public void getMessage(String message) {
//        log.info("received message='{}'", message);
//        latch.countDown();
//    }

    @KafkaListener(id = "test", topics = "sample.topic", group = "group1")
    public void getMessage(@Payload String data,
                                 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partitionId,
                                 Acknowledgment ack) {
        log.debug("data= {}", data);
        ack.acknowledge();
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
