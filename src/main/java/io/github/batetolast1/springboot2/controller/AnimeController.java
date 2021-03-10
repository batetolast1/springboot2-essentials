package io.github.batetolast1.springboot2.controller;

import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.service.AnimeService;
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
    private final AnimeService animeService;

    @GetMapping("/find")
    public ResponseEntity<List<Anime>> listAll() {
        log.info(utils.formatLocalDateTime(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll());
    }

    @GetMapping(value = "/find", params = "id")
    public ResponseEntity<Anime> findById(int id) {
        return ResponseEntity.ok(animeService.findById(id));
    }

    @GetMapping(value = "/find", params = "name")
    public ResponseEntity<List<Anime>> findByName(String name) {
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping("/save")
    public ResponseEntity<Anime> save(@RequestBody Anime anime) {
        return ResponseEntity.ok(animeService.save(anime));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Anime anime) {
        animeService.update(anime);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
