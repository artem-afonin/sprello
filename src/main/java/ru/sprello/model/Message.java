package ru.sprello.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "text", length = 256, nullable = false)
    private String text;
}
