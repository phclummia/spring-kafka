package com.currencyfeed.consumer;

import com.currencyfeed.consumer.service.ConsumerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);

        ConsumerService service = (ConsumerService) context.getBean("consumerService");
        service.listen();
    }

}
