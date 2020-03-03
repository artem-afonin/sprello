package ru.sprello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sprello.model.User;
import ru.sprello.model.board.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByUsersContaining(User user);
}
