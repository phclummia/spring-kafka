package com.currencyfeed.producer.integration;

import com.currencyfeed.producer.model.ExchangeInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BinanceRestApi {

    @Value("${binance.service.rootUri}")
    private String rootUri;
    private final RestTemplate restTemplate;

    public ExchangeInfo getSymbol(String symbol) {
        log.info("Getting Symbol : {}", symbol);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(rootUri);
        builder.queryParam("symbol", symbol);

        return Optional.of(restTemplate.getForEntity(builder.toUriString(), ExchangeInfo.class))
                .map(HttpEntity::getBody)
                .orElse(null);

    }

}
