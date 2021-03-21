package io.github.batetolast1.springboot2.util;

import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.domain.Cover;
import io.github.batetolast1.springboot2.domain.Publisher;
import io.github.batetolast1.springboot2.repository.AnimeRepository;
import io.github.batetolast1.springboot2.repository.CoverRepository;
import io.github.batetolast1.springboot2.repository.PublisherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Utils {

    public String formatLocalDateTime(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss").format(localDateTime);
    }

    public Anime findAnimeOrThrowNotFound(Long id, AnimeRepository animeRepository) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found."));
    }

    public Publisher findPublisherOrThrowNorFound(Long id, PublisherRepository publisherRepository) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found."));
    }

    public Cover findCoverOrThrowNorFound(Long id, CoverRepository coverRepository) {
        return coverRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cover not found."));
    }
}
