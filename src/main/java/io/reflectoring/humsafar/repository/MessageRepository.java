package io.reflectoring.humsafar.repository;

import io.reflectoring.humsafar.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository  extends JpaRepository<Message, Long> {
}
