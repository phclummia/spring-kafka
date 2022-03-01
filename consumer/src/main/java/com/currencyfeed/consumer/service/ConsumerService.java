package com.currencyfeed.consumer.service;

import com.currencyfeed.common.utils.IDataSerialization;
import com.currencyfeed.consumer.model.ExchangeInfo;
import com.currencyfeed.consumer.model.ExchangeInfoOld;
import com.currencyfeed.consumer.repository.ExchangeInfoOldRepository;
import com.currencyfeed.consumer.repository.ExchangeInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {


    @Value("${spring.kafka.producer.bootstrap-servers}")
    private List<String> bootstrapServers;
    @Value("${producerService.groupId}")
    private String groupId;
    @Value("${producerService.topicName}")
    private String topicName;

    private final IDataSerialization<ExchangeInfo> dataSerialization;
    private final IDataSerialization<ExchangeInfoOld> dataSerializationOld;
    private final ExchangeInfoRepository repository;
    private final ExchangeInfoOldRepository repositoryOld;


    @KafkaListener(id = "${producerService.groupId}", topics = "${producerService.topicName}", clientIdPrefix = "client-with-annotation")
    public void listen(@Payload String message,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("client-with-annotation Consuming message. Partition Id : {}, Message : {}", partition, message);

        ExchangeInfo des = dataSerialization.deserialize(message);
        repository.save(des);
    }

    public void listen() {


        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId + "Old");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(topicName));
        consumer.poll(0);
        consumer.seekToBeginning(consumer.assignment());

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);


                for (ConsumerRecord<String, String> record : records) {
                    log.info("Consuming message. Offset : {}, Partition : {}, Message : {}", record.offset(), record.partition(), record.value());
                    ExchangeInfoOld des = dataSerializationOld.deserialize(record.value());
                    repositoryOld.save(des);
                }


            }
        } finally {
            consumer.close();
        }

    }

}
