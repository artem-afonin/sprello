package ru.sprello.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.sprello.model.board.Board;
import ru.sprello.utils.Views;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(Views.PrivateBoard.class)
    private Long id;

    @Column(name = "text", length = 256, nullable = false)
    @JsonView(Views.PrivateBoard.class)
    private String text;

    @ManyToOne
    @JsonView(Views.PrivateBoard.class)
    private User author;

    @Column(name = "post_time")
    @JsonView(Views.PrivateBoard.class)
    private LocalDateTime posted;

    @ManyToOne
    @JsonView(Views.PrivateBoard.class)
    private Board board;
}
