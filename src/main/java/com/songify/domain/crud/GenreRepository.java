package com.songify.domain.crud;

import org.springframework.data.repository.Repository;

import java.util.Optional;

interface GenreRepository extends Repository<Genre, Long> {

    Optional<Genre> findById(Long id);
}
