package io.github.batetolast1.springboot2.client;

import io.github.batetolast1.springboot2.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class AnimeClient {

    public static void main(String[] args) {
        var animeResponseEntity = new RestTemplate().getForEntity("http://localhost:8080/animes/find?id={id}", Anime.class, 5);
        log.info("ResponseEntity {}", animeResponseEntity);
        log.info("ResponseBody {}", animeResponseEntity.getBody());

        var anime = new RestTemplate().getForObject("http://localhost:8080/animes/find?id={id}", Anime.class, 5);
        log.info("Anime {}", anime);

        var animeArray = new RestTemplate().getForObject("http://localhost:8080/animes/find", Anime[].class);
        log.info("Anime array {}", Arrays.toString(animeArray));

        var animeListResponseEntity = new RestTemplate().exchange("http://localhost:8080/animes/find", HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>() {});
        log.info("Anime list {}", animeListResponseEntity.getBody());
    }
}
