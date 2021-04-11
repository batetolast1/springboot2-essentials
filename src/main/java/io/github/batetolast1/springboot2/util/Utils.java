package io.github.batetolast1.springboot2.util;

import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.domain.Cover;
import io.github.batetolast1.springboot2.domain.Publisher;
import io.github.batetolast1.springboot2.exception.ResourceNotFoundException;
import io.github.batetolast1.springboot2.repository.AnimeRepository;
import io.github.batetolast1.springboot2.repository.CoverRepository;
import io.github.batetolast1.springboot2.repository.PublisherRepository;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    public Anime findAnimeOrThrowNotFound(Long id, AnimeRepository animeRepository) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anime not found."));
    }

    public Publisher findPublisherOrThrowNorFound(Long id, PublisherRepository publisherRepository) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found."));
    }

    public Cover findCoverOrThrowNorFound(Long id, CoverRepository coverRepository) {
        return coverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cover not found."));
    }
}
