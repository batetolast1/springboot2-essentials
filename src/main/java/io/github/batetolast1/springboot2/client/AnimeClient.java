package io.github.batetolast1.springboot2.client;

import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.domain.AnimeType;
import io.github.batetolast1.springboot2.wrapper.PageableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class AnimeClient {

    public static void main(String[] args) {
        var anime = Anime.builder()
                .name("Overlord")
                .releaseDate(LocalDate.now())
                .type(AnimeType.JOSEI)
                .build();

        var animeSaved = new RestTemplate().postForObject(
                "http://localhost:8080/animes/save", anime, Anime.class);

        if (animeSaved != null) {
            log.info("Anime saved id {}", animeSaved.getId());
        }

        var anime2 = Anime.builder()
                .name("Steins Gate")
                .releaseDate(LocalDate.now())
                .type(AnimeType.JOSEI)
                .build();

        Anime anime2saved = new RestTemplate().exchange(
                "http://localhost:8080/animes/save", HttpMethod.POST, new HttpEntity<>(anime2, createJsonHeader()), Anime.class)
                .getBody();

        if (anime2saved != null) {
            log.info("Anime saved id {}", anime2saved.getId());

            anime2saved.setName("Steins Gate Zero");
            ResponseEntity<Void> exchangeUpdated = new RestTemplate().exchange(
                    "http://localhost:8080/animes/update", HttpMethod.PUT, new HttpEntity<>(anime2saved, createJsonHeader()), Void.class);

            log.info("Anime updated status {} ", exchangeUpdated.getStatusCode());

            ResponseEntity<Void> exchangeDeleted = new RestTemplate().exchange(
                    "http://localhost:8080/animes/delete?id={id}", HttpMethod.DELETE, null, Void.class, anime2saved.getId());

            log.info("Anime deleted status {} ", exchangeDeleted.getStatusCode());
        }
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    private static void testGetWithRestTemplate() {
        var animeResponseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/animes/find?id={id}", Anime.class, 5);
        log.info("ResponseEntity {}", animeResponseEntity);
        log.info("ResponseBody {}", animeResponseEntity.getBody());

        var anime = new RestTemplate().getForObject(
                "http://localhost:8080/animes/find?id={id}", Anime.class, 5);
        log.info("Anime {}", anime);

        var animeArray = new RestTemplate().getForObject(
                "http://localhost:8080/animes/find", Anime[].class);
        log.info("Anime array {}", Arrays.toString(animeArray));

        var animeListResponseEntity = new RestTemplate().exchange(
                "http://localhost:8080/animes/find", HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>() {});
        log.info("Anime list {}", animeListResponseEntity.getBody());

        var animePageableResponseEntity = new RestTemplate().exchange(
                "http://localhost:8080/animes/findPageable", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<Anime>>() {});
        log.info("Anime pageableResponse body {}", animePageableResponseEntity.getBody());
    }
}
