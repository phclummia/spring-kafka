package com.currencyfeed.producer.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class ExchangeInfo {
    private String timezone;
    private Long serverTime;
    private List<RateLimit> rateLimits;
    private List<SymbolInfo> symbols;
}
