package io.github.batetolast1.springboot2.repository;

import io.github.batetolast1.springboot2.domain.Anime;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

    @Override
    @EntityGraph(attributePaths = {"publisher", "covers"})
    List<Anime> findAll();

    @Override
    @EntityGraph(attributePaths = {"publisher", "covers"})
    Optional<Anime> findById(Long aLong);

    @EntityGraph(attributePaths = {"publisher", "covers"})
    List<Anime> findByName(String name);
}
