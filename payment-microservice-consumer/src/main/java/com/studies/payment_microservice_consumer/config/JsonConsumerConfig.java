package com.studies.payment_microservice_consumer.config;

import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.JsonMessageConverter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class JsonConsumerConfig {

	private final KafkaProperties kafkaProperties;
	
	@Bean
	public ConsumerFactory<String, Object> jsonConsumerFactory(){
		var configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory(configs);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory jsonContainerFactory(ConsumerFactory<String, Object> jsonConsumerFactory) {
		var factory = new ConcurrentKafkaListenerContainerFactory<String, Object>();
		factory.setConsumerFactory(jsonConsumerFactory);
		factory.setMessageConverter(new JsonMessageConverter());
		return factory;
	}
}
