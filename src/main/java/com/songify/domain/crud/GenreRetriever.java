package com.songify.domain.crud;

import com.songify.domain.crud.dto.GenreDto;
import com.songify.domain.crud.exception.GenreNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class GenreRetriever {

    private final GenreRepository genreRepository;

    public GenreDto findGenreDtoById(final long genreId) {
        return genreRepository.findById(genreId)
                .map(genre -> GenreDto.builder().genreId(genreId).build())
                .orElseThrow(() -> new GenreNotFoundException(genreId));
    }

    public Genre findGenreById(final long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
    }
}
