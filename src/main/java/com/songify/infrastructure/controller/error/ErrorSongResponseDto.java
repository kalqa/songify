package com.songify.infrastructure.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorSongResponseDto(String message, HttpStatus status) {
}
