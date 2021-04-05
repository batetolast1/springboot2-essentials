package io.github.batetolast1.springboot2.repository;

import io.github.batetolast1.springboot2.domain.Anime;

import java.util.List;

public interface CustomAnimeRepository {

    List<Anime> fetchByIdIn(List<Long> animeIds);
}
