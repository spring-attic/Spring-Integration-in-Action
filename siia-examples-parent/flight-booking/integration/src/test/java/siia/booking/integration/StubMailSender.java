package siia.booking.integration;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class StubMailSender implements JavaMailSender {

	private final AtomicLong counter = new AtomicLong();


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
		counter.incrementAndGet();
	}

	public void send(SimpleMailMessage[] messages) throws MailException {
		if (messages != null) {
			counter.addAndGet(messages.length);
		}
	}
	
}
