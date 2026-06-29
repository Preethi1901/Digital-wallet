package com.example.transactions.services;

import com.example.transactions.Entities.Notification;
import com.example.transactions.Repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    public  void saveNotification(Long userId, String message){
        Notification notification= Notification.builder()
                                    .userId(userId).message(message).status("SENT")
                                    .createdAt(LocalDateTime.now())
                                    .build();
        notificationRepository.save(notification);
    }
}
