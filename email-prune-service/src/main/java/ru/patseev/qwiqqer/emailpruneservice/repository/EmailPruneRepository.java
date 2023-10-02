package ru.patseev.qwiqqer.emailpruneservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.patseev.qwiqqer.emailpruneservice.model.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmailPruneRepository extends JpaRepository<UserEntity, Long> {

	@Query(value = "FROM UserEntity WHERE isActivated = false AND emailSendingTime < :activationTime")
	List<UserEntity> findAllUsersWithInactiveEmail(@Param("activationTime") LocalDateTime activationTime);
}
