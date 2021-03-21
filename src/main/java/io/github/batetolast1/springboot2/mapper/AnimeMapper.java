package io.github.batetolast1.springboot2.mapper;

import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.dto.AnimeDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {PublisherMapper.class, CoverMapper.class})
public interface AnimeMapper {

    Anime mapToAnime(AnimeDTO animeDTO);

    AnimeDTO mapToAnimeDTO(Anime anime);

    List<AnimeDTO> mapToAnimeDTOsList(List<Anime> animeList);
}
