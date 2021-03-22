package io.github.batetolast1.springboot2.domain;

import io.github.batetolast1.springboot2.converter.AnimeTypeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Publisher publisher;
    @Convert(converter = AnimeTypeConverter.class)
    private AnimeType type;
    @OneToMany
    private Set<Cover> covers;
    private LocalDate releaseDate;
}
