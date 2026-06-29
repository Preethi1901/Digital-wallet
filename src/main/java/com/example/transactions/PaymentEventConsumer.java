package com.example.transactions;

import com.example.transactions.DTO.PaymentCompletedEvent;
import com.example.transactions.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventConsumer {
    private final NotificationService notificationService;
    @KafkaListener(
            topics="payment.completed",
            id="notifications"

    )
    public void consume(PaymentCompletedEvent event){
        System.out.println(
                "Payment Received: " + event);
        notificationService.saveNotification(
                event.getSenderUserId(),
                "₹" + event.getAmount()
                        + " debited successfully.");

        notificationService.saveNotification(
                event.getReceiverUserId(),
                "₹" + event.getAmount()
                        + " credited successfully.");
    }


}
