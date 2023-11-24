package com.songify.domain.crud.exception;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(final long artistId) {
        super("Artist with id " + artistId + " not found");
    }
}
