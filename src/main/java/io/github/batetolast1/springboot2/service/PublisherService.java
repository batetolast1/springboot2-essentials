package io.github.batetolast1.springboot2.service;

import io.github.batetolast1.springboot2.domain.Publisher;
import io.github.batetolast1.springboot2.repository.PublisherRepository;
import io.github.batetolast1.springboot2.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final Utils utils;

    public Publisher findById(Long id) {
        return utils.findPublisherOrThrowNorFound(id, publisherRepository);
    }
}
