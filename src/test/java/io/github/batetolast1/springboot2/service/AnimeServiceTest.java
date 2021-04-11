package io.github.batetolast1.springboot2.service;

import io.github.batetolast1.springboot2.Util.AnimeCreator;
import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.exception.ResourceNotFoundException;
import io.github.batetolast1.springboot2.repository.AnimeRepository;
import io.github.batetolast1.springboot2.util.Utils;
import jdk.jfr.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Anime Service test")
class AnimeServiceTest {

    @InjectMocks
    private AnimeService animeService;
    @Mock
    private AnimeRepository animeRepositoryMock;
    @Mock
    private Utils utilsMock;

    @BeforeEach
    void setup() {
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(animePage);
        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.findByNameContaining(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeRepositoryMock.save(AnimeCreator.createAnimeToBeSaved()))
                .thenReturn(AnimeCreator.createValidAnime());
        BDDMockito.when(animeRepositoryMock.save(AnimeCreator.createValidAnimeToBeUpdated()))
                .thenReturn(AnimeCreator.createValidUpdatedAnime());
        BDDMockito.doNothing()
                .when(animeRepositoryMock)
                .delete(AnimeCreator.createValidAnime());

        BDDMockito.when(utilsMock.findAnimeOrThrowNotFound(
                ArgumentMatchers.eq(AnimeCreator.createValidAnime().getId()),
                ArgumentMatchers.any(AnimeRepository.class)))
                .thenReturn(AnimeCreator.createValidAnime());
        BDDMockito.when(utilsMock.findAnimeOrThrowNotFound(
                ArgumentMatchers.eq(AnimeCreator.createNonExistingAnime().getId()),
                ArgumentMatchers.any(AnimeRepository.class)))
                .thenThrow(ResourceNotFoundException.class);
        BDDMockito.when(utilsMock.findAnimeOrThrowNotFound(
                ArgumentMatchers.eq(AnimeCreator.createValidAnimeToBeUpdated().getId()),
                ArgumentMatchers.any(AnimeRepository.class)))
                .thenReturn(AnimeCreator.createValidUpdatedAnime());
    }

    @Test
    @Name("listAllPageable() returns a pageable list of animes when successful")
    void should_ReturnListOfAnimesInsidePageObject_When_ListAllPageable() {
        String expectedName = AnimeCreator.createValidAnime().getName();

        Page<Anime> animePage = animeService.listAll(PageRequest.of(0, 5));

        assertThat(animePage).isNotNull();
        assertThat(animePage.toList()).isNotEmpty();
        assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @Name("findById() returns an anime when successful")
    void should_ReturnAnime_When_FindById() {
        Long expectedId = AnimeCreator.createValidAnime().getId();

        Anime anime = animeService.findById(AnimeCreator.createValidAnime().getId());

        assertThat(anime).isNotNull();
        assertThat(anime.getId()).isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @Name("findByName() returns an anime list when successful")
    void should_ReturnAnimeList_When_FindByName() {
        String expectedName = AnimeCreator.createValidAnime().getName();

        List<Anime> animeList = animeService.findByName(AnimeCreator.createValidAnime().getName());

        assertThat(animeList).isNotNull()
                .isNotEmpty();
        assertThat(animeList.get(0)).isNotNull();
        assertThat(animeList.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @Name("save() returns an anime when successful")
    void should_ReturnAnime_When_Save() {
        Long expectedId = AnimeCreator.createValidAnime().getId();

        Anime anime = animeService.save(AnimeCreator.createAnimeToBeSaved());

        assertThat(anime).isNotNull();
        assertThat(anime.getId()).isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @Name("delete() removes an anime when deleting existing anime")
    void should_RemoveAnime_When_DeleteValidAnime() {
        assertThatCode(() -> animeService.delete(AnimeCreator.createValidAnime().getId()))
                .doesNotThrowAnyException();
    }

    @Test
    @Name("delete() throws ResourceNotFound when an anime doesn't exist")
    void should_ThrowResourceNotFound_When_DeleteNonExistingAnime() {
        Long nonExistingAnimeId = AnimeCreator.createNonExistingAnime().getId();
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> animeService.delete(nonExistingAnimeId));
    }

    @Test
    @Name("update() returns void when successful")
    void should_ReturnVoid_When_UpdateValidAnime() {
        assertThatCode(() -> animeService.update(AnimeCreator.createValidAnimeToBeUpdated()))
                .doesNotThrowAnyException();
    }

    @Test
    @Name("update() throws ResourceNotFound when an anime doesn't exist")
    void should_ThrowResourceNotFound_When_UpdateNonExistingAnime() {
        Anime nonExistingAnime = AnimeCreator.createNonExistingAnime();
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> animeService.update(nonExistingAnime));
    }
}