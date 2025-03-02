package com.anu3dev.backend.service;

import com.anu3dev.backend.dao.EmailOTPDAO;
import com.anu3dev.backend.model.EmailOTP;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailOTPService implements IEmailOTPService {
	@Autowired
	private JavaMailSender sender;
	@Value("{spring.mail.username}")
	private String fromEmailid;
	@Autowired
	private EmailOTPDAO dao;

	@Override
	public String sendOTPEmail(EmailOTP email) throws Exception {

		MimeMessage mimeMessage = sender.createMimeMessage();

		MimeMessageHelper emailMessage = new MimeMessageHelper(mimeMessage, true);
		emailMessage.setFrom(fromEmailid);
		emailMessage.setCc("boss@company.com");
		emailMessage.setTo(email.getEmailId());
		emailMessage.setSubject("We have received your query");
		emailMessage.setText(email.getMessage());
		emailMessage.setSentDate(new Date());
		emailMessage.addAttachment("welcome-kit.pdf", new ClassPathResource("welcome-kit.pdf"));
		sender.send(mimeMessage);

		dao.save(email);

		return "We have received your query.";
	}
}
