package com.futura.Purple.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendSimpleEmail(String toEmail, String body1,String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("kishorelesnar77@gmail.com");
		message.setTo(toEmail);
		message.setText(body1);
		message.setSubject(subject);
		mailSender.send(message);
		System.out.println("Mail Sent");
	}
	
	
	public void quizCompletionMail(String toEmail, String link) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("kishorelesnar77@gmail.com");
		message.setTo(toEmail);
		message.setText("Thank You for your participation in the quiz. Please download the result pdf in the given link. Thank You "+link);
		message.setSubject("Quiz Completion Mail");
		mailSender.send(message);
		System.out.println(" Completion Mail Sent");
	}
	
	public void quizTeacherCompletionMail(String toEmail, String link) {
		SimpleMailMessage message = new SimpleMailMessage();
	   message.setFrom("kishorelesnar77@gmail.com");
		message.setTo(toEmail);
		message.setText("A participant in the quiz you're in charge of has completed their quiz. Please download the result pdf in the given link. Thank You "+link);
		message.setSubject("Quiz Completion Mail");
		mailSender.send(message);
		System.out.println(" Completion Mail Sent");
	}

}