package io.github.batetolast1.springboot2.service;

import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.repository.AnimeRepository;
import io.github.batetolast1.springboot2.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final PublisherService publisherService;
    private final CoverService coverService;
    private final Utils utils;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findById(Long id) {
        return utils.findAnimeOrThrowNotFound(id, animeRepository);
    }

    public Anime save(Anime anime) {
        anime.setPublisher(publisherService.findById(anime.getPublisher().getId()));
        anime.setCovers(
                anime.getCovers()
                        .stream()
                        .map(cover -> coverService.findById(cover.getId()))
                        .collect(Collectors.toSet())
        );
        return animeRepository.save(anime);
    }

    public void delete(Long id) {
        animeRepository.delete(utils.findAnimeOrThrowNotFound(id, animeRepository));
    }

    public void update(Anime anime) {
        utils.findAnimeOrThrowNotFound(anime.getId(), animeRepository);
        animeRepository.save(anime);
    }
}
