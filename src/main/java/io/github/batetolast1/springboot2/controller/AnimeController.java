package io.github.batetolast1.springboot2.controller;

import io.github.batetolast1.springboot2.domain.Anime;
import io.github.batetolast1.springboot2.dto.AnimeDTO;
import io.github.batetolast1.springboot2.mapper.AnimeMapper;
import io.github.batetolast1.springboot2.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeService animeService;
    private final AnimeMapper animeMapper;

    @GetMapping("/find")
    public ResponseEntity<List<AnimeDTO>> listAll() {
        return ResponseEntity.ok(animeMapper.mapToAnimeDTOsList(animeService.listAll()));
    }

    @GetMapping("/findPageable")
    public ResponseEntity<Page<Anime>> listAll(Pageable pageable) {
        return ResponseEntity.ok(animeService.listAll(pageable));
    }

    @GetMapping("/findPageableSingleQueryForAllChildEntities")
    public ResponseEntity<List<AnimeDTO>> listAllPageableSingleQueryForAllChildEntities(Pageable pageable) {
        return ResponseEntity.ok(
                animeMapper.mapToAnimeDTOsList(animeService.listAllPageableSingleQueryForAllChildEntities(pageable)));
    }

    @GetMapping("/findPageableSingleQueryPerEachChildEntity")
    public ResponseEntity<List<AnimeDTO>> listAllPageableSingleQueryPerEachChildEntity(Pageable pageable) {
        return ResponseEntity.ok(
                animeMapper.mapToAnimeDTOsList(animeService.listAllPageableSingleQueryPerEachChildEntity(pageable)));
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
    public ResponseEntity<AnimeDTO> save(@RequestBody @Valid AnimeDTO animeDTO) {
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
