package ru.patseev.qwiqqer.messagingservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.patseev.qwiqqer.messagingservice.model.dto.MessageRequest;

@Service
@RequiredArgsConstructor
public class MessagingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessagingService.class);

	@RabbitListener(queues = "${qwiqqer.queue.name}")
	public void sendLetterToEmail(MessageRequest messageRequest){
		LOGGER.info("Send letter to email -> `{}`, username -> `{}`", messageRequest.getEmail(), messageRequest.getUsername());
		LOGGER.info("The letter was successfully delivered.");
	}
}
