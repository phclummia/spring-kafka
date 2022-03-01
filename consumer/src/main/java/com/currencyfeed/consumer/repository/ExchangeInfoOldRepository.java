package com.currencyfeed.consumer.repository;

import com.currencyfeed.consumer.model.ExchangeInfoOld;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExchangeInfoOldRepository extends MongoRepository<ExchangeInfoOld, String> {
}
