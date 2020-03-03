package ru.sprello.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.sprello.config.WebSecurityConfig;
import ru.sprello.model.board.Board;
import ru.sprello.utils.Views;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Модель, описывающая пользователя веб-сервиса
 *
 * @author Vasiliy Vintovkin
 */
@Data
@Entity
@Table(name = "usr")
public class User {
    /**
     * Уникальный идентификатор пользователя
     */
    @Id
    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private String id;

    /**
     * Имя пользователя.
     * Имя и фамилия, которые приходят с OAuth2 авторизации
     *
     * @see WebSecurityConfig
     */
    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private String name;

    /**
     * Ссылка на изображение профиля
     * Ссылка приходит с OAuth2 авторизации
     *
     * @see WebSecurityConfig
     */
    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private String userpic;

    /**
     * Дата последнего визита
     */
    @JsonView(Views.PublicSimple.class)
    @Getter
    @Setter
    private LocalDateTime lastVisit;

    /**
     * Коллеция всех ролей, к которым относится пользователь
     *
     * @see Role
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @NotNull
    @Getter
    @Setter
    private Set<Role> roles;

    /**
     * Коллекция всех досок, в которых состоит пользователь
     *
     * @see Board
     */
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonView(Views.PublicExtendedUser.class)
    @Getter
    @Setter
    private Set<Board> boards;
}
