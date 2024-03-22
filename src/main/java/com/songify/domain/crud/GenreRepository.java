package com.songify.domain.crud;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.Set;

interface GenreRepository extends Repository<Genre, Long> {

    Optional<Genre> findById(Long id);

    @Modifying
    @Query("delete from Genre g where g.id = :id")
    int deleteById(Long id);

    Genre save(Genre genre);

    Set<Genre> findAll();
}
