package com.songify.domain.crud.artist;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ArtistRepository extends Repository<Artist, Long> {
    Optional<Artist> findById(Long id);

    @Transactional
    @Modifying
    @Query("delete from Artist a where a.id = ?1")
    void deleteById(Long id);

}
