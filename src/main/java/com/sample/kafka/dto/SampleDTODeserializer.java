package com.sample.kafka.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;


public class SampleDTODeserializer implements Deserializer<SampleDTO> {
    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    @Override
    public SampleDTO deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        SampleDTO user = null;
        try {
            user = mapper.readValue(arg1, SampleDTO.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }
}
