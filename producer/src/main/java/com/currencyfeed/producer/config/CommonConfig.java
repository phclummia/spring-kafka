package com.currencyfeed.producer.config;

import com.currencyfeed.common.utils.DataSerialization;
import com.currencyfeed.common.utils.IDataSerialization;
import com.currencyfeed.producer.model.ExchangeInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    IDataSerialization<ExchangeInfo> exchangeInfoSerialization(){
        return new DataSerialization<>(ExchangeInfo.class);
    }
}
