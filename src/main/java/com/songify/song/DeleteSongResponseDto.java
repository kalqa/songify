package com.songify.song;

import org.springframework.http.HttpStatus;

public record DeleteSongResponseDto(String message, HttpStatus status) {
}
