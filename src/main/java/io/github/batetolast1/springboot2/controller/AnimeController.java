package io.github.batetolast1.springboot2.controller;

import io.github.batetolast1.springboot2.dto.AnimeDTO;
import io.github.batetolast1.springboot2.mapper.AnimeMapper;
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
    private final AnimeMapper animeMapper;

    @GetMapping("/find")
    public ResponseEntity<List<AnimeDTO>> listAll() {
        log.info(utils.formatLocalDateTime(LocalDateTime.now()));
        return ResponseEntity.ok(animeMapper.mapToAnimeDTOsList(animeService.listAll()));
    }

    @GetMapping(value = "/find", params = "id")
    public ResponseEntity<AnimeDTO> findById(@RequestParam Long id) {
        return ResponseEntity.ok(animeMapper.mapToAnimeDTO(animeService.findById(id)));
    }

    @GetMapping(value = "/find", params = "name")
    public ResponseEntity<List<AnimeDTO>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(animeMapper.mapToAnimeDTOsList(animeService.findByName(name)));
    }

    @PostMapping("/save")
    public ResponseEntity<AnimeDTO> save(@RequestBody AnimeDTO animeDTO) {
        return ResponseEntity.ok(animeMapper.mapToAnimeDTO(animeService.save(animeMapper.mapToAnime(animeDTO))));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody AnimeDTO animeDTO) {
        animeService.update(animeMapper.mapToAnime(animeDTO));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
