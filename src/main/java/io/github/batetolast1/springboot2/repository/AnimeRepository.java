package io.github.batetolast1.springboot2.repository;

import io.github.batetolast1.springboot2.domain.Anime;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
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

    public List<Anime> listAll() {
        return animes;
    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextInt(4, 100000));
        animes.add(anime);
        return anime;
    }
}
