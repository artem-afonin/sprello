package ru.sprello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sprello.model.Task;

public interface TaskRepository extends JpaRepository<Task, String> {

}
