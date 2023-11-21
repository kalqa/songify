package com.songify.domain.crud.genre;

import com.songify.domain.crud.genre.dto.GenreDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class GenreCrudFacade {

    private final GenreRepository genreRepository;

    public GenreDto findGenreById(final long genreId) {
        return genreRepository.findById(genreId)
                .map(genre -> GenreDto.builder().genreId(genreId).build())
                .orElseThrow(() -> new GenreNotFoundException(genreId));
    }
}
