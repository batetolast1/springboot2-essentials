package io.github.batetolast1.springboot2.Util;

import io.github.batetolast1.springboot2.domain.AnimeType;
import io.github.batetolast1.springboot2.dto.AnimeDTO;

import java.time.LocalDate;

public class AnimeDTOCreator {

    public static AnimeDTO createAnimeDTOToBeSaved() {
        return AnimeDTO.builder()
                .name("Test anime DTO")
                .releaseDate(LocalDate.now())
                .type(AnimeType.JOSEI.name())
                .build();
    }

    public static AnimeDTO createValidAnimeDTO() {
        return AnimeDTO.builder()
                .id(0L)
                .name("Test anime DTO")
                .releaseDate(LocalDate.now())
                .type(AnimeType.JOSEI.name())
                .build();
    }

    public static AnimeDTO createValidAnimeDTOToBeUpdated() {
        return createValidAnimeDTO();
    }

    public static AnimeDTO createValidUpdatedAnimeDTO() {
        return AnimeDTO.builder()
                .id(0L)
                .name("Test anime DTO")
                .releaseDate(LocalDate.now())
                .type(AnimeType.JOSEI.name())
                .build();
    }
}
