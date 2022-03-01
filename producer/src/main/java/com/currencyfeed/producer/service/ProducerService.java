package com.currencyfeed.producer.service;

import com.currencyfeed.common.utils.IDataSerialization;
import com.currencyfeed.producer.integration.BinanceRestApi;
import com.currencyfeed.producer.model.ExchangeInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerService {

    @Value("#{'${producerService.symbols}'.split(',')}")
    private List<String> symbolsConfig;
    @Value("${producerService.topicName}")
    private String topicName;

    private final BinanceRestApi binanceRestApi;
    private final KafkaTemplate kafkaTemplate;

    private final IDataSerialization<ExchangeInfo> dataSerialization;


    public void execute() {
        log.info("Execute Start");
        List<ExchangeInfo> symbols = getSymbols();
        produceMessage(symbols);
        log.info("Execute End");
    }

    public void produceMessage(List<ExchangeInfo> symbols) {
        log.info("Produce Message Start");
        symbols.stream()
                .map(s -> new ProducerRecord(
                        topicName, dataSerialization.serialize(s)))
                .forEach(kafkaTemplate::send);
        log.info("Produce Message End");
    }


    private List<ExchangeInfo> getSymbols() {
        log.info("Get Symbols Start");
        List<ExchangeInfo> symbols = new ArrayList<>();

        symbolsConfig.stream()
                .forEach(s -> {
                    ExchangeInfo item = binanceRestApi.getSymbol(s);
                    if (item != null) {
                        symbols.add(item);
                    }
                });

        return symbols;

    }
}
