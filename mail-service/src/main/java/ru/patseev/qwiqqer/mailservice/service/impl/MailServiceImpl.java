package ru.patseev.qwiqqer.mailservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.patseev.qwiqqer.mailservice.model.dto.MessageRequest;
import ru.patseev.qwiqqer.mailservice.service.MailService;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
	private final JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String mailUsername;
	private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

	@Override
	@RabbitListener(queues = "${qwiqqer.queue.name}")
	public void sendLetterToEmail(MessageRequest messageRequest) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailUsername);
		//TODO: Тут нужно использовать имейл, под которым зарегистрировался пользователь
		message.setTo("serega.patseev@gmail.com");
		message.setSubject("Go to the specified address to complete registration!");
		message.setText(String.format("Dear %s, go to complete registration --> " +
						"http://localhost:8888/api/v1/users?access_code=%s",
				messageRequest.getUsername(),
				messageRequest.getPersonalCode()));

		mailSender.send(message);
		LOGGER.info("The letter was successfully delivered to email {}!", messageRequest.getEmail());
	}
}