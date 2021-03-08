package io.github.batetolast1.springboot2.repository;

import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AnimeRepository {

    private static final List<Anime> animes;

    static {
        animes = new ArrayList<>(
                List.of(
                        new Anime(1, "FMA"),
                        new Anime(2, "Berserk"),
                        new Anime(3, "Naruto")
                )
        );
    }

    private final Utils utils;

    public List<Anime> listAll() {
        return animes.stream()
                .sorted(Comparator.comparingInt(Anime::getId))
                .collect(Collectors.toList());
    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextInt(4, 100000));
        animes.add(anime);
        return anime;
    }

    public void delete(int id) {
        animes.remove(utils.findAnimeOrThrowNotFound(id, animes));
    }

    public Anime findById(int id) {
        return utils.findAnimeOrThrowNotFound(id, animes);
    }

    public void update(Anime anime) {
        animes.remove(utils.findAnimeOrThrowNotFound(anime.getId(), animes));
        animes.add(anime);
    }
}
