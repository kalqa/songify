package com.songify.song.error;

import org.springframework.http.HttpStatus;

public record ErrorDeleteSongResponseDto(String message, HttpStatus status) {
}
