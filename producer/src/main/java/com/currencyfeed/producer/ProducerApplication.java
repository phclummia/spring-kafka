package com.currencyfeed.producer;

import com.currencyfeed.producer.service.ProducerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProducerApplication {



	public static void main(String[] args) throws InterruptedException {

		ApplicationContext context = SpringApplication.run(ProducerApplication.class, args);

		ProducerService service = (ProducerService)context.getBean("producerService");

		while(true){
			service.execute();
			Thread.sleep(10000);
		}


	}

}
