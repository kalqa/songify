package com.songify.infrastructure.crud.album.apivalidation;

import com.songify.domain.crud.exception.AlbumNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
class AlbumApiErrorHandler {

    @ExceptionHandler(AlbumNotFoundException.class)
    ResponseEntity<AlbumApiErrorResponseDto> handleAlbumNotFoundException(AlbumNotFoundException exception) {
        return ResponseEntity.status(NOT_FOUND).body(new AlbumApiErrorResponseDto(
                exception.getMessage(), NOT_FOUND
        ));
    }

}
