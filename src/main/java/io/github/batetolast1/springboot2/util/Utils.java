package io.github.batetolast1.springboot2.util;

import io.github.batetolast1.springboot2.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class Utils {

    public String formatLocalDateTime(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss").format(localDateTime);
    }

    public Anime findAnimeOrThrowNotFound(int id, List<Anime> animes) {
        return animes.stream()
                .filter(anime -> anime.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found."));
    }
}
