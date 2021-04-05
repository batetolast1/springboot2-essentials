package io.github.batetolast1.springboot2.repository;

import io.github.batetolast1.springboot2.domain.Anime;
import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomAnimeRepositoryImpl implements CustomAnimeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Anime> fetchByIdIn(List<Long> animeIds) {
        List<Anime> animeList = entityManager.createQuery("""
                        select distinct a
                        from Anime a
                        left join fetch a.publisher
                        where a.id in :animeIds
                        """
                , Anime.class)
                .setParameter("animeIds", animeIds)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();

        animeList = entityManager.createQuery("""
                        select distinct a
                        from Anime a
                        left join fetch a.covers
                        where a in :animeList
                        """
                , Anime.class)
                .setParameter("animeList", animeList)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();
        return animeList;
    }
}
