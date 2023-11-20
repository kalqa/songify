package com.songify.domain.crud.genre;

import com.songify.domain.crud.genre.query.SimpleGenreQueryDto;
import org.springframework.data.repository.Repository;

import java.util.Optional;

interface GenreQueryRepository extends Repository<SimpleGenreQueryDto, Long> {

    Optional<SimpleGenreQueryDto> findById(Long id);
}
