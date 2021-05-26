package c.service;

import c.model.EmailService;
import c.util.ReaderProperties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailtrapService implements EmailService {

    private final Session session;

    public MailtrapService() {

        Properties props = ReaderProperties.read("c/settings/mail.properties");

        session = Session.getInstance(
                props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                props.getProperty("username"),
                                props.getProperty("password")
                        );
                    }
                }
        );

    }

    @Override
    public void send(String from, String to, String subject, String body) {

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
