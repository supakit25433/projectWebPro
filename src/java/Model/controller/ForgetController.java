/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.controller;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author nar-u
 */
public class ForgetController {

    private static final String SENDER = "quizantonline@gmail.com";
    private static final String PASSWORD = "S2u5P4a3.kit";
    private static final String SUBJECT = "Your password in QuizAnt!!!";

    public ForgetController() {

    }

    public static void sendEmail(String recipientEmail, String yourUsername, String yourPassword) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "465");
//        properties.put("mail.smtp.socketFactory.port", "465");
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.smtp.socketFactory.fallback", "false");
        
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER, PASSWORD);
            }
        });

        try {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(SENDER));
            msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail)
            );
            msg.setSubject(SUBJECT);
            msg.setSentDate(new Date());
            msg.setText("\nThank you for use QuizAnt \n\n"
                    + "\t Your USERNAME is " + yourUsername
                    + "\n\n\t Your PASSWORD IS " + yourPassword);

            Transport.send(msg);

            System.out.println("Done");

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}
