package com.currencyfeed.consumer.repository;

import com.currencyfeed.consumer.model.ExchangeInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExchangeInfoRepository extends MongoRepository<ExchangeInfo, String> {
}
