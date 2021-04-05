package io.github.batetolast1.springboot2.repository;

import io.github.batetolast1.springboot2.domain.Anime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnimeRepository extends JpaRepository<Anime, Long>, CustomAnimeRepository {

    @Override
    @EntityGraph(value = "graph.Anime.allAttributes")
    List<Anime> findAll();

    /**
     * HHH000104: firstResult/maxResults specified with collection fetch; applying in memory!
     */
    @Override
    @EntityGraph(value = "graph.Anime.allAttributes")
    Page<Anime> findAll(Pageable pageable);

    @Query("SELECT id FROM Anime")
    List<Long> findIds(Pageable pageable);

    @EntityGraph(value = "graph.Anime.allAttributes")
    List<Anime> findByIdIn(List<Long> animeIds, Sort sort);

    @Override
    @EntityGraph(value = "graph.Anime.allAttributes")
    Optional<Anime> findById(Long id);

    @EntityGraph(value = "graph.Anime.allAttributes")
    List<Anime> findByName(String name);

    @EntityGraph(value = "graph.Anime.allAttributes")
    List<Anime> findByNameContaining(String name);
}
