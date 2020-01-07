package ru.sprello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sprello.model.User;

public interface UserDetailsRepo extends JpaRepository<User, String>{

}
