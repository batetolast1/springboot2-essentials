package io.github.batetolast1.springboot2.converter;

import io.github.batetolast1.springboot2.domain.AnimeType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class AnimeTypeConverter implements AttributeConverter<AnimeType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AnimeType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public AnimeType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(AnimeType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
