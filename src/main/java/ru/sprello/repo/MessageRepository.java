package ru.sprello.repo;

import org.springframework.data.repository.CrudRepository;
import ru.sprello.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
