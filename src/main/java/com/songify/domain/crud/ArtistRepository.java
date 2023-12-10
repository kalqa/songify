package com.songify.domain.crud;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.Set;

interface ArtistRepository extends Repository<Artist, Long> {

    @Modifying
    @Query("delete from Artist a where a.id = :id")
    int deleteById(Long id);

    Artist save(Artist artist);

    Set<Artist> findAll(Pageable pageable);

    Optional<Artist> findById(Long artistId);
}
