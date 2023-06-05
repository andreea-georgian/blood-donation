package com.example.demo.service.notificationfactory;

import com.example.demo.entity.Appointment;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailNotificationSender implements NotificationSender {
    @Override
    public void sendConfirmation(Appointment appointment){
        String to = appointment.getDonor().getEmail();
        String from = "donation.blood.bank.project@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("donation.blood.bank.project@gmail.com", "tswnbseemmffgugg");

            }

        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Blood Donation Appointment");
            String messageText = ("Hello " + appointment.getDonor().getFirstName() + ",\nYou have an appointment on " + appointment.getDate() + "\nHave a nice day! :)");
            message.setText(messageText);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    @Override
    public void sendReminder(Appointment appointment) {
        String to = appointment.getDonor().getEmail();
        String from = "donation.blood.bank.project@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("donation.blood.bank.project@gmail.com", "tswnbseemmffgugg");

            }

        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Blood Donation");
            String messageText = ("Hello " + appointment.getDonor().getFirstName() + ",\nHalf a year has already passed since we haven't seen each other. From today you can safely donate blood again! :)");
            message.setText(messageText);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    @Override
    public void sendNotification(String contact) {
        String to = contact;
        String from = "donation.blood.bank.project@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("donation.blood.bank.project@gmail.com", "tswnbseemmffgugg");

            }

        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Blood Donation");
            String messageText = ("Hello,\n" + "Blood is needed in your county! We would be happy if you had time to visit the center! :)");
            message.setText(messageText);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
