package siia.business;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * @author Marius Bogoevici
 */
public class StubMailSender implements JavaMailSender {

    private final AtomicLong counter = new AtomicLong();
    private volatile String lastMessageText;

    public long getCount() {
        return counter.get();
    }

    public MimeMessage createMimeMessage() {
        return null;
    }

    public MimeMessage createMimeMessage(InputStream inputStream) throws MailException {
        return null;
    }

    public void send(MimeMessage message) throws MailException {
        counter.incrementAndGet();
    }

    public void send(MimeMessage[] messages) throws MailException {
        if (messages != null) {
            counter.addAndGet(messages.length);
        }
    }

    public void send(MimeMessagePreparator preparator) throws MailException {
        counter.incrementAndGet();
    }

    public void send(MimeMessagePreparator[] preparators) throws MailException {
        if (preparators != null) {
            counter.addAndGet(preparators.length);
        }
    }

    public void send(SimpleMailMessage message) throws MailException {
        this.lastMessageText = message.getText();
        counter.incrementAndGet();
    }

    public void send(SimpleMailMessage[] messages) throws MailException {
        if (messages != null) {
            counter.addAndGet(messages.length);
        }
    }

    public String getLastMessageText() {
        return this.lastMessageText;
    }
}
