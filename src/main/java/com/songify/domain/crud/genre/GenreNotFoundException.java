package com.songify.domain.crud.genre;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(final long genreId) {
        super("Genre with id " + genreId + " not found");
    }
}
