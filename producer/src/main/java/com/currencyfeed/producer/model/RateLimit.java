package com.currencyfeed.producer.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class RateLimit {
    private String rateLimitType;
    private String interval;
    private Long intervalNum;
    private Long limit;
}
