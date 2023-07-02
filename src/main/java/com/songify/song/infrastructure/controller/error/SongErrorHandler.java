package com.songify.song.infrastructure.controller.error;

import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.infrastructure.controller.SongRestController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = SongRestController.class)
@Log4j2
public class SongErrorHandler {

    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity<ErrorSongResponseDto> handleException(SongNotFoundException exception) {
        log.warn("SongNotFoundException while accessing song");
        ErrorSongResponseDto errorSongResponseDto = new ErrorSongResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorSongResponseDto);
    }
}
