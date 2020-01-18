package ru.sprello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sprello.model.board.Task;

public interface TaskRepository extends JpaRepository<Task, String> {

}
