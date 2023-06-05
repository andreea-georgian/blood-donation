package com.example.demo.service.notificationfactory;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Donor;

public interface NotificationSender {
    void sendConfirmation(Appointment appointment);
    void sendReminder(Appointment appointment);
    void sendNotification(String contact);
}
