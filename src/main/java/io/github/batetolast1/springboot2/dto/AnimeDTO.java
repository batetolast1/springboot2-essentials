package io.github.batetolast1.springboot2.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AnimeDTO {

    private Long id;
    private String name;
    private PublisherDTO publisher;
    private String type;
    private List<CoverDTO> covers;
    private LocalDate releaseDate;
}
