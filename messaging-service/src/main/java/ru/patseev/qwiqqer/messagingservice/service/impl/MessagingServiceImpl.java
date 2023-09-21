package ru.patseev.qwiqqer.messagingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.patseev.qwiqqer.messagingservice.model.dto.MessageRequest;
import ru.patseev.qwiqqer.messagingservice.service.MessagingService;

@Service
@RequiredArgsConstructor
public class MessagingServiceImpl implements MessagingService {
	private final JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String mailUsername;
	private static final Logger LOGGER = LoggerFactory.getLogger(MessagingServiceImpl.class);

	@Override
	@RabbitListener(queues = "${qwiqqer.queue.name}")
	public void sendLetterToEmail(MessageRequest messageRequest){

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailUsername);
		message.setTo("serega.patseev@gmail.com");
		message.setSubject("Successfully registration!");
		message.setText(String.format("%s you are successfully registered.", messageRequest.getUsername()));
		mailSender.send(message);

		LOGGER.info("The letter was successfully delivered to email {}.", messageRequest.getEmail());
	}
}
