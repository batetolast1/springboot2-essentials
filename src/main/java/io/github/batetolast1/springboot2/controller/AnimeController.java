package io.github.batetolast1.springboot2.controller;

import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.repository.AnimeRepository;
import io.github.batetolast1.springboot2.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {

    private final Utils utils;
    private final AnimeRepository animeRepository;

    @GetMapping
    public ResponseEntity<List<Anime>> listAll() {
        log.info(utils.formatLocalDateTime(LocalDateTime.now()));
        return ResponseEntity.ok(animeRepository.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable int id) {
        return ResponseEntity.ok(animeRepository.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Anime> save(@RequestBody Anime anime) {
        return ResponseEntity.ok(animeRepository.save(anime));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        animeRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Anime anime) {
        animeRepository.update(anime);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
