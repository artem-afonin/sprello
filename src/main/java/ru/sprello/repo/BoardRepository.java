package ru.sprello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sprello.model.User;

public interface BoardRepository extends JpaRepository<User, String>{

}
