package com.sample.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName(value = "SampleDTO")
public class SampleDTO {
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
}
