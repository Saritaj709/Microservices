package com.bridgelabz.microservices.user.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.microservices.user.model.MailDTO;
import com.bridgelabz.microservices.user.repository.UserRepository;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public boolean sendMail(MailDTO mail) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(mail.getTo());

			helper.setSubject(mail.getSubject());
			helper.setText(mail.getText());
			mailSender.send(message);
			System.out.println("mail sent");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
}
