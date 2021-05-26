package c.model;

public interface EmailService {
    void send(String from, String to, String subject, String body);
}
