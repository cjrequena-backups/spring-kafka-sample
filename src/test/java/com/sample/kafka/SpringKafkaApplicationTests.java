package com.sample.kafka;

import static org.assertj.core.api.Assertions.assertThat;

import com.sample.kafka.dto.SampleDTO;
import com.sample.kafka.service.KafkaConsumer;
import com.sample.kafka.service.KafkaPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaApplicationTests {

    @Autowired
    private KafkaPublisher kafkaPublisher;

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Test
    public void testReceiver() throws Exception {
        kafkaPublisher.sendMessage("helloworld.t", new SampleDTO("ID","NAME"));

        kafkaConsumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(kafkaConsumer.getLatch().getCount()).isEqualTo(0);
    }
}