package io.github.batetolast1.springboot2.controller;

import io.github.batetolast1.springboot2.Util.AnimeCreator;
import io.github.batetolast1.springboot2.Util.AnimeDTOCreator;
import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.dto.AnimeDTO;
import io.github.batetolast1.springboot2.mapper.AnimeMapper;
import io.github.batetolast1.springboot2.service.AnimeService;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DisplayName("Anime Controller test")
class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;
    @Mock
    private AnimeService animeServiceMock;
    @Mock
    private AnimeMapper animeMapper;

    @BeforeEach
    void setup() {
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any(Pageable.class)))
                .thenReturn(animePage);
        BDDMockito.when(animeServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(AnimeCreator.createValidAnime());
        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeServiceMock.save(AnimeCreator.createAnimeToBeSaved()))
                .thenReturn(AnimeCreator.createValidAnime());
        BDDMockito.doNothing()
                .when(animeServiceMock)
                .update(AnimeCreator.createValidAnimeToBeUpdated());
        BDDMockito.doNothing()
                .when(animeServiceMock)
                .delete(AnimeCreator.createValidAnime().getId());

        BDDMockito.when(animeMapper.mapToAnimeDTO(AnimeCreator.createValidAnime()))
                .thenReturn(AnimeDTOCreator.createValidAnimeDTO());
        BDDMockito.when(animeMapper.mapToAnimeDTO(AnimeCreator.createValidUpdatedAnime()))
                .thenReturn(AnimeDTOCreator.createValidUpdatedAnimeDTO());
        BDDMockito.when(animeMapper.mapToAnimeDTOsList(ArgumentMatchers.anyList()))
                .thenReturn(List.of(AnimeDTOCreator.createValidAnimeDTO()));
        BDDMockito.when(animeMapper.mapToAnime(AnimeDTOCreator.createAnimeDTOToBeSaved()))
                .thenReturn(AnimeCreator.createAnimeToBeSaved());
        BDDMockito.when(animeMapper.mapToAnime(AnimeDTOCreator.createValidAnimeDTOToBeUpdated()))
                .thenReturn(AnimeCreator.createValidAnimeToBeUpdated());
    }

    @Test
    @Name("listAllPageable() returns a pageable list of animes when successful")
    void should_ReturnListOfAnimesInsidePageObject_When_ListAllPageable() {
        String expectedName = AnimeCreator.createValidAnime().getName();

        Page<Anime> animePage = animeController.listAll(PageRequest.of(0, 5)).getBody();

        assertThat(animePage).isNotNull();
        assertThat(animePage.toList()).isNotEmpty();
        assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @Name("findById() returns an anime DTO when successful")
    void should_ReturnAnimeDTO_When_FindById() {
        Long expectedId = AnimeDTOCreator.createValidAnimeDTO().getId();

        AnimeDTO animeDTO = animeController.findById(AnimeDTOCreator.createValidAnimeDTO().getId()).getBody();

        assertThat(animeDTO).isNotNull();
        assertThat(animeDTO.getId()).isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @Name("findByName() returns an anime DTO list when successful")
    void should_ReturnAnimeDTOList_When_FindByName() {
        String expectedName = AnimeDTOCreator.createValidAnimeDTO().getName();

        List<AnimeDTO> animeDTOList = animeController.findByName(AnimeDTOCreator.createValidAnimeDTO().getName()).getBody();

        assertThat(animeDTOList).isNotNull()
                .isNotEmpty();
        assertThat(animeDTOList.get(0)).isNotNull();
        assertThat(animeDTOList.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @Name("save() returns an anime DTO when successful")
    void should_ReturnAnimeDTO_When_Save() {
        Long expectedId = AnimeDTOCreator.createValidAnimeDTO().getId();

        AnimeDTO animeDTO = animeController.save(AnimeDTOCreator.createAnimeDTOToBeSaved()).getBody();

        assertThat(animeDTO).isNotNull();
        assertThat(animeDTO.getId()).isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @Name("delete() returns void ResponseEntity when anime exists")
    void should_ReturnVoidResponseEntity_When_Delete() {
        ResponseEntity<Void> responseEntity = animeController.delete(ArgumentMatchers.anyLong());

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @Name("update() returns void ResponseEntity when anime exists")
    void should_ReturnVoidResponseEntity_When_Update() {
        ResponseEntity<Void> responseEntity = animeController.update(AnimeDTOCreator.createValidAnimeDTO());

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(responseEntity.getBody()).isNull();
    }
}