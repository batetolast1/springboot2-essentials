package io.github.batetolast1.springboot2.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AnimeDTO {

    private Long id;
    @NotBlank
    private String name;
    private PublisherDTO publisher;
    @NotBlank
    private String type;
    private List<CoverDTO> covers;
    private LocalDate releaseDate;
}
