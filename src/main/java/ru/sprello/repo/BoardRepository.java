package ru.sprello.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sprello.model.board.Board;
import ru.sprello.model.User;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, String> {
    Board findById(Long id);
    List<Board> findAllByIsPrivateFalse();
    List<Board> findAllByUsersContaining(User user);
}
