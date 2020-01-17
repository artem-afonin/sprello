package ru.sprello.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "text", length = 256, nullable = false)
    private String text;
    @ManyToOne
    private User author;
    @Column(name = "post_time")
    private LocalDateTime posted;
    @ManyToOne
    private Board board;
}
