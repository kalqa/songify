package com.songify.domain.crud.genre;

import com.songify.domain.crud.genre.query.SimpleGenreQueryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class GenreCrudFacade {

    private final GenreQueryRepository genreQueryRepository;

    public SimpleGenreQueryDto findGenreById(final long genreId) {
        return genreQueryRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
    }
}
