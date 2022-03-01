package com.currencyfeed.consumer.config;

import com.currencyfeed.common.utils.DataSerialization;
import com.currencyfeed.common.utils.IDataSerialization;
import com.currencyfeed.consumer.model.ExchangeInfo;
import com.currencyfeed.consumer.model.ExchangeInfoOld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    IDataSerialization<ExchangeInfo> exchangeInfoSerialization(){
        return new DataSerialization<>(ExchangeInfo.class);
    }

    @Bean
    IDataSerialization<ExchangeInfoOld> exchangeInfoSerializationOld(){
        return new DataSerialization<>(ExchangeInfoOld.class);
    }
}
