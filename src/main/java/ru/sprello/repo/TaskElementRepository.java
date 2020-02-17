package ru.sprello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sprello.model.board.TaskElement;

public interface TaskElementRepository extends JpaRepository<TaskElement, Long> {

}
