package io.github.batetolast1.springboot2.Util;

import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.domain.AnimeType;

import java.time.LocalDate;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("Test anime")
                .releaseDate(LocalDate.now())
                .type(AnimeType.JOSEI)
                .build();
    }

    public static Anime createValidAnime() {
        return Anime.builder()
                .id(0L)
                .name("Test anime")
                .releaseDate(LocalDate.now())
                .type(AnimeType.JOSEI)
                .build();
    }

    public static Anime createValidAnimeToBeUpdated() {
        return createValidAnime();
    }

    public static Anime createValidUpdatedAnime() {
        return Anime.builder()
                .id(0L)
                .name("Updated test anime")
                .releaseDate(LocalDate.now())
                .type(AnimeType.JOSEI)
                .build();
    }

    public static Anime createNonExistingAnime() {
        return Anime.builder()
                .id(-1L)
                .build();
    }
}
