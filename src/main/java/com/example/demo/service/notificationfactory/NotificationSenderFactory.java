package com.example.demo.service.notificationfactory;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Donor;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationSenderFactory {
    @Autowired
    AppointmentService appointmentService;
    public void create(Appointment appointment) {
        if (appointment.getDonor().getEmailNotification().equals(true)) {
            EmailNotificationSender emailNotificationSender = new EmailNotificationSender();
            emailNotificationSender.sendConfirmation(appointment);
        }
        if(appointment.getDonor().getSmsNotification().equals(true)) {
            SmsNotificationSender smsNotificationSender = new SmsNotificationSender();
            smsNotificationSender.sendConfirmation(appointment);
        }
    }

    @Scheduled(cron = "0 26 6 * * ?")
    public void reminder() {
        LocalDate currentDate = LocalDate.now().plusDays(1);
        List<Appointment> appointments = appointmentService.findAllByDate(java.sql.Date.valueOf(currentDate));
        EmailNotificationSender emailNotificationSender = new EmailNotificationSender();
        SmsNotificationSender smsNotificationSender = new SmsNotificationSender();

        for(Appointment a:appointments) {
            if (a.getDonor().getEmailNotification().equals(true)) {
                emailNotificationSender.sendConfirmation(a);
            }
            if(a.getDonor().getSmsNotification().equals(true)) {
                smsNotificationSender.sendConfirmation(a);
            }
        }
    }

    @Scheduled(cron = "0 0 11 * * ?")
    public void reminderForComeback() {
        LocalDate currentDate = LocalDate.now().plusDays(183);
        List<Appointment> appointments = appointmentService.findAllByDate(java.sql.Date.valueOf(currentDate));
        EmailNotificationSender emailNotificationSender = new EmailNotificationSender();
        SmsNotificationSender smsNotificationSender = new SmsNotificationSender();

        for(Appointment a:appointments) {
            if (a.getDonor().getEmailNotification().equals(true)) {
                emailNotificationSender.sendReminder(a);
            }
            if(a.getDonor().getSmsNotification().equals(true)) {
                smsNotificationSender.sendReminder(a);
            }
        }
    }

    public void sendNotification(Donor donor) {
        if(donor.getEmailNotification().equals(true)) {
            EmailNotificationSender emailNotificationSender = new EmailNotificationSender();
            emailNotificationSender.sendNotification(donor.getEmail());
        }
        if(donor.getSmsNotification().equals(true)) {
            SmsNotificationSender smsNotificationSender = new SmsNotificationSender();
            smsNotificationSender.sendNotification(donor.getPhoneNumber());
        }
    }
}
