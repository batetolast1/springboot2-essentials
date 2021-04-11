package io.github.batetolast1.springboot2.repository;

import io.github.batetolast1.springboot2.Util.AnimeCreator;
import io.github.batetolast1.springboot2.domain.Anime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DataJpaTest
@DisplayName("Anime JPA Repository test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("save() creates an anime when successful")
    void should_PersistAnime_When_Save() {
        Anime anime = AnimeCreator.createAnimeToBeSaved();

        Anime savedAnime = animeRepository.save(anime);

        assertThat(savedAnime.getId()).isNotNull();
        assertThat(savedAnime.getName()).isNotNull();
        assertThat(savedAnime.getName()).isEqualTo(anime.getName());
    }

    @Test
    @DisplayName("save() updates an anime when successful")
    void should_UpdatePersistedAnime_When_Save() {
        Anime anime = AnimeCreator.createAnimeToBeSaved();

        Anime savedAnime = animeRepository.save(anime);

        savedAnime.setName("Updated name");

        Anime updatedAnime = animeRepository.save(savedAnime);

        assertThat(savedAnime.getId()).isNotNull();
        assertThat(savedAnime.getName()).isNotNull();
        assertThat(savedAnime.getName()).isEqualTo(updatedAnime.getName());
    }

    @Test
    @DisplayName("save() throws ConstraintViolationException when an anime name is empty")
    void should_ThrowConstraintViolationException_When_NameIsEmpty() {
        Anime anime = new Anime();

        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> animeRepository.save(anime));
    }

    @Test
    @DisplayName("delete() removes persisted anime when successful")
    void should_DeletePersistedAnime_When_Delete() {
        Anime anime = AnimeCreator.createAnimeToBeSaved();

        Anime savedAnime = animeRepository.save(anime);

        animeRepository.delete(savedAnime);

        Optional<Anime> optionalAnime = animeRepository.findById(savedAnime.getId());

        assertThat(optionalAnime).isNotPresent();
    }

    @Test
    @DisplayName("findByName() finds an anime by name when successful")
    void should_FindByNamePersistedAnime_When_FindById() {
        Anime anime = AnimeCreator.createAnimeToBeSaved();

        Anime savedAnime = animeRepository.save(anime);

        String name = savedAnime.getName();

        List<Anime> animeList = animeRepository.findByName(name);

        assertThat(animeList).isNotEmpty()
                .contains(savedAnime);
    }

    @Test
    @DisplayName("findByName() returns empty list when no anime is found")
    void should_ReturnEmptyList_When_FindById() {
        List<Anime> animeList = animeRepository.findByName("name");

        assertThat(animeList).isEmpty();
    }
}