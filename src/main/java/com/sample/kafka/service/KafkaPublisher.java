package com.sample.kafka.service;

import com.sample.kafka.dto.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.converter.KafkaMessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;


@Service
@Log4j2
public class KafkaPublisher {

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    public void sendMessage(String topic, SampleDTO message) {

        // the KafkaTemplate provides asynchronous send methods returning a
        // Future
        Map<String, String> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, topic);
        //headers.put(KafkaHeaders.RECEIVED_TOPIC, "helloworld.t");
        //headers.put(KafkaMessageHeaders.CONTENT_TYPE,"application/json");
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(MessageBuilder.withPayload(message).copyHeaders(headers).build());
        //ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, message);



        // you can register a callback with the listener to receive the result
        // of the send asynchronously
        future.addCallback(
                new ListenableFutureCallback<SendResult<Integer, String>>() {

                    @Override
                    public void onSuccess(SendResult<Integer, String> result) {
                        log.info("sent message='{}' with offset={}", message, result.getRecordMetadata().offset());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("unable to send message='{}'", message, ex);
                    }
                }
        );

        // alternatively, to block the sending thread, to await the result,
        // invoke the future’s get() method
    }

}
