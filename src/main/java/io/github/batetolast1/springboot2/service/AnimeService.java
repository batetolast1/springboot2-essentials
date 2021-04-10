package io.github.batetolast1.springboot2.service;

import com.google.common.collect.Ordering;
import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.repository.AnimeRepository;
import io.github.batetolast1.springboot2.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Log4j2
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final PublisherService publisherService;
    private final CoverService coverService;
    private final Utils utils;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    /**
     * Get list of fetched entities with SQL using cartesian product; also keeping order
     * <p>
     * Not optimal solution, see:
     * HHH000104: firstResult/maxResults specified with collection fetch; applying in memory!
     */
    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    /**
     * Get list of fetched entities with SQL using cartesian product; also keeping order
     * Optimal for small number of child collection entities
     */
    public List<Anime> listAllPageableSingleQueryForAllChildEntities(Pageable pageable) {
        List<Long> ids = animeRepository.findIds(pageable);

        return animeRepository.findByIdIn(ids, pageable.getSort());
    }

    /**
     * Get list of fetched entities without SQL using creating cartesian product; also keeping order
     * Optimal for large number of child collection entities
     * <p>
     * Sorting here, Pageable not supported in TypedQuery
     */
    public List<Anime> listAllPageableSingleQueryPerEachChildEntity(Pageable pageable) {
        List<Long> ids = animeRepository.findIds(pageable);

        List<Anime> animeList = animeRepository.fetchByIdIn(ids);

        animeList.sort(Ordering.explicit(ids)
                .onResultOf(Anime::getId));

        return animeList;
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByNameContaining(name);
    }

    public Anime findById(Long id) {
        return utils.findAnimeOrThrowNotFound(id, animeRepository);
    }

    @Transactional
    public Anime save(Anime anime) {
        if (anime.getPublisher() != null) {
            anime.setPublisher(publisherService.findById(anime.getPublisher().getId()));
        }

        if (anime.getCovers() != null) {
            anime.setCovers(
                    anime.getCovers()
                            .stream()
                            .map(cover -> coverService.findById(cover.getId()))
                            .collect(Collectors.toList())
            );
        }

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
