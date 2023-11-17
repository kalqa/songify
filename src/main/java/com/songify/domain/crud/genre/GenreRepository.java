package com.songify.domain.crud.genre;

import com.songify.domain.crud.genre.dto.GenreInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

interface GenreRepository extends Repository<Genre, Long> {

    @Query("SELECT s FROM Genre s WHERE s.id =:id")
    Optional<GenreInfo> findById(Long id);
}
