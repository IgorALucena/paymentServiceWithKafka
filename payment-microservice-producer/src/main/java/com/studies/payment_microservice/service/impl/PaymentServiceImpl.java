package com.studies.payment_microservice.service.impl;

import java.io.Serializable;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.studies.payment_microservice.model.Payment;
import com.studies.payment_microservice.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class PaymentServiceImpl implements PaymentService {

	private final KafkaTemplate<String, Serializable> kafkaTemplate;

	@SneakyThrows
	@Override
	public void sendPayment(Payment payment) {
		log.info("Recebi o pagamento {}", payment);
		Thread.sleep(1000);

		log.info("Enviando pagamento...");
		kafkaTemplate.send("payment-topic", payment);
	}

}
