package io.github.batetolast1.springboot2.service;

import io.github.batetolast1.springboot2.domain.Cover;
import io.github.batetolast1.springboot2.repository.CoverRepository;
import io.github.batetolast1.springboot2.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoverService {

    private final CoverRepository coverRepository;
    private final Utils utils;

    public Cover findById(Long id) {
        return utils.findCoverOrThrowNorFound(id, coverRepository);
    }
}
