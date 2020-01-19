package ru.sprello.model;

import com.fasterxml.jackson.annotation.JsonView;
import ru.sprello.model.board.Board;

/**
 * Используется для ограничения доступа к данным моделей через JSON.<br/>
 * Модели аннотируются с помощью {@link JsonView}
 *
 * @see JsonView
 */
public final class Views {
    /**
     * Основные данные для моделей верхнего уровня
     *
     * @see User
     * @see Board
     */
    public interface PublicSimple {
    }

    /**
     * Основные данные для моделей верхнего уровня
     * и расширенные для класса {@link Board}
     *
     * @see User
     * @see Board
     */
    public interface PublicExtendedBoard extends PublicSimple {
    }

    /**
     * Основные данные для моделей верхнего уровня
     * и расширенные для класса {@link User}
     *
     * @see User
     * @see Board
     */
    public interface PublicExtendedUser extends PublicSimple {
    }
}
