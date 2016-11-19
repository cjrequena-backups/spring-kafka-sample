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
@Service
public class KafkaConsumer {

    private CountDownLatch latch = new CountDownLatch(1);

//    @KafkaListener(topics = "helloworld.t")
//    public void receiveMessage(SampleDTO dto) {
//        log.info("received message='{}'", dto);
//        latch.countDown();
//    }

//    @KafkaListener(topics = "helloworld.t")
//    public void receiveMessage(String message) {
//        log.info("received message='{}'", message);
//        latch.countDown();
//    }

    @KafkaListener(id = "testInChannel", topics = "helloworld.t")
    public void consumeInMessage(@Payload String data,
                                 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partitionId,
                                 Acknowledgment ack) {
        log.info("[testInChannel]data=" + data);
        ack.acknowledge();
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
