package io.github.batetolast1.springboot2.mapper;

import io.github.batetolast1.springboot2.domain.Cover;
import io.github.batetolast1.springboot2.dto.CoverDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CoverMapper {

    Cover mapToCover(CoverDTO coverDTO);

    CoverDTO mapToCoverDTO(Cover cover);
}
