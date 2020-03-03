package ru.sprello.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ru.sprello.model.board.Board;
import ru.sprello.utils.Views;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Модель, описывающая сообщение в чате доски
 *
 * @see Board
 * @see User
 */
@Entity
@Table(name = "messages")
public class Message {
    /**
     * Уникальный идентификатор сообщения
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private Long id;

    /**
     * Содержание сообщения
     */
    @Column(name = "text", length = 256, nullable = false)
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private String text;

    /**
     * Автор сообщения
     */
    @ManyToOne
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private User author;

    /**
     * Дата создания сообщения
     */
    @Column(name = "post_time")
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private LocalDateTime posted;

    /**
     * Доска, в чате которой было опубликовано данное сообщение
     */
    @ManyToOne
    @JsonView(Views.PrivateBoard.class)
    @Getter
    @Setter
    private Board board;
}
