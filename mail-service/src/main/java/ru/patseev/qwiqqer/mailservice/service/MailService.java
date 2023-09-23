package ru.patseev.qwiqqer.mailservice.service;

import ru.patseev.qwiqqer.mailservice.model.dto.MessageRequest;

public interface MailService {

	void sendLetterToEmail(MessageRequest messageRequest);
}
