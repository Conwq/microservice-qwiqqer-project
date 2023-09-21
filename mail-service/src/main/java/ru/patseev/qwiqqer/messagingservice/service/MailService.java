package ru.patseev.qwiqqer.messagingservice.service;

import ru.patseev.qwiqqer.messagingservice.model.dto.MessageRequest;

public interface MailService {

	void sendLetterToEmail(MessageRequest messageRequest);
}
