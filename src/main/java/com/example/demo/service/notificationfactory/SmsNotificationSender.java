package com.example.demo.service.notificationfactory;

import com.example.demo.entity.Appointment;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsNotificationSender implements NotificationSender{
    @Override
    public void sendConfirmation(Appointment appointment) {
        Twilio.init("AC38a1ca925f32b61a652311fcf8fb34ff", "f4c9e8526b80fc9c9676453984d18873");
        String messageText = ("Hello " + appointment.getDonor().getFirstName() + ",\nYou have an appointment on " + appointment.getDate() + "\nHave a nice day! :)");
        Message message = Message.creator(
                        new PhoneNumber("+4" + appointment.getDonor().getPhoneNumber()),
                        new PhoneNumber("+13612735438"),
                        messageText)
                .create();
    }

    @Override
    public void sendReminder(Appointment appointment) {
        Twilio.init("AC38a1ca925f32b61a652311fcf8fb34ff", "f4c9e8526b80fc9c9676453984d18873");
        String messageText = ("Hello " + appointment.getDonor().getFirstName() + ",\nHalf a year has already passed since we haven't seen each other. From today you can safely donate blood again! :)");
        Message message = Message.creator(
                        new PhoneNumber("+4" + appointment.getDonor().getPhoneNumber()),
                        new PhoneNumber("+13612735438"),
                        messageText)
                .create();
    }

    @Override
    public void sendNotification(String contact) {
        Twilio.init("AC38a1ca925f32b61a652311fcf8fb34ff", "f4c9e8526b80fc9c9676453984d18873");
        String messageText = ("Hello,\n" + "Blood is needed in your county! We would be happy if you had time to visit the center! :)");
        Message message = Message.creator(
                        new PhoneNumber("+4" + contact),
                        new PhoneNumber("+13612735438"),
                        messageText)
                .create();

    }
}
