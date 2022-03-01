package com.currencyfeed.producer.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Filter {

    private String filterType;
    private String minPrice;
    private String maxPrice;
    private String tickSize;
}
