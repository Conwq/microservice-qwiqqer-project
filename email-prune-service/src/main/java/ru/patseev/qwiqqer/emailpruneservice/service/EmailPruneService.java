package ru.patseev.qwiqqer.emailpruneservice.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patseev.qwiqqer.emailpruneservice.model.entity.UserEntity;
import ru.patseev.qwiqqer.emailpruneservice.repository.EmailPruneRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailPruneService {
	private final EmailPruneRepository pruneRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailPruneService.class);

	@Transactional
	@Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 24)
	public void pruneAllUsersByInactiveEmail(){
		List<UserEntity> allUsersWithInactiveEmail = pruneRepository
				.findAllUsersWithInactiveEmail(LocalDateTime.now().minusHours(20));

		pruneRepository.deleteAll(allUsersWithInactiveEmail);
		LOGGER.warn("Prune users from inactive email!");
	}
}
