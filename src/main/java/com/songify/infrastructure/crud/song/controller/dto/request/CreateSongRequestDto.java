package com.songify.infrastructure.crud.song.controller.dto.request;

import com.songify.domain.crud.song.dto.SongLanguageDto;
import com.songify.infrastructure.apivalidation.EnumNamePattern;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateSongRequestDto(
        @NotNull(message = "name must not be null")
        @NotEmpty(message = "name must not be empty")
        String songName,

        @NotNull(message = "artist must not be null")
        @NotEmpty(message = "artist must not be empty")
        String artist,

        @NotNull(message = "albumId must not be null")
        Long albumId,

        @NotNull(message = "genreId must not be null")
        Long genreId,

        @EnumNamePattern(regexp = "ENGLISH|SPANISH|POLISH|FRENCH|GERMAN|ITALIAN|JAPANESE|OTHER")
        SongLanguageDto language
) {
}
