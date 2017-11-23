package com.sample.kafka;

import com.sample.kafka.dto.SampleDTO;
import com.sample.kafka.service.KafkaSubscriber;
import com.sample.kafka.service.KafkaPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaApplicationTests {

    @Autowired
    private KafkaPublisher kafkaPublisher;

    @Autowired
    private KafkaSubscriber kafkaConsumer;

    @Test
    public void testReceiver() throws Exception {
        kafkaPublisher.sendMessage("sample.topic", new SampleDTO("ID","NAME"));

        kafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(kafkaConsumer.getLatch().getCount()).isEqualTo(0);
    }
}