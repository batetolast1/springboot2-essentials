package io.github.batetolast1.springboot2.repository;

import io.github.batetolast1.springboot2.domain.Cover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverRepository extends JpaRepository<Cover, Long> {
}
