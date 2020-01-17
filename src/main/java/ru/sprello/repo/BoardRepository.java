package ru.sprello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sprello.model.Board;
import ru.sprello.model.User;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, String> {
    public Board findById(Long id);
    public List<Board> findAllByIsPrivateFalse();
    public List<Board> findAllByUsersContaining(User user);
}
