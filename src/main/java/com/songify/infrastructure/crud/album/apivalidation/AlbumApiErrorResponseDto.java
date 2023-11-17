package com.songify.infrastructure.crud.album.apivalidation;

import org.springframework.http.HttpStatus;

record AlbumApiErrorResponseDto(String message, HttpStatus status) {
}
