package com.example.transactions;

import com.example.transactions.DTO.PaymentCompletedEvent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentEventProducer {
    private final KafkaTemplate<String,Object> kafkaTemplate;
    public void publishPaymentCompleted(PaymentCompletedEvent event){
        kafkaTemplate.send("payment.completed",event);

        System.out.println("publish method");

    }
}
