package io.github.batetolast1.springboot2.mapper;

import io.github.batetolast1.springboot2.domain.Publisher;
import io.github.batetolast1.springboot2.dto.PublisherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    Publisher mapToPublisher(PublisherDTO publisherDTO);

    PublisherDTO mapToPublisherDTO(Publisher publisher);
}
