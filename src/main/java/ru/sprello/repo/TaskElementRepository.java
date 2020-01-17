package ru.sprello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sprello.model.TaskElement;

public interface TaskElementRepository extends JpaRepository<TaskElement, String> {

}
