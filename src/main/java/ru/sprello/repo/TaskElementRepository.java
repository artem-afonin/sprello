package ru.sprello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sprello.model.User;

public interface TaskElementRepository extends JpaRepository<User, String>{

}
