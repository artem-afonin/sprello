package ru.sprello.utils;

import org.springframework.core.convert.converter.Converter;
import ru.sprello.model.board.Color;

/**
 * Класс-конвертер для преобразования enum Color в строку в верхнем регистре
 *
 * @see Color
 */
public class StringToEnumConverter implements Converter<String, Color> {
    @Override
    public Color convert(String value) {
        return Color.valueOf(value.toUpperCase());
    }
}
