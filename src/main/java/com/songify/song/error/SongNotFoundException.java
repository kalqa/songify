package com.songify.song.error;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(String message) {
        super(message);
    }
}
