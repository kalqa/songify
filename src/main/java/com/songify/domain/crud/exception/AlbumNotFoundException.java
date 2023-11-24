package com.songify.domain.crud.exception;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(final long albumId) {
        super("Album with id " + albumId + " not found");
    }
}
