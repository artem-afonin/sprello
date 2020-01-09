package ru.sprello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sprello.model.User;

public interface UserRepository extends JpaRepository<User, String>{

}
