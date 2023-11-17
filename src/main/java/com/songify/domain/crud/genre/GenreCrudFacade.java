package com.songify.domain.crud.genre;

import com.songify.domain.crud.genre.dto.GenreInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreCrudFacade {

    private final GenreRepository genreRepository;

    public GenreInfo findGenreById(final long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
    }
}
