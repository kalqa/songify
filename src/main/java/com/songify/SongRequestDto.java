package com.songify;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SongRequestDto(
        @NotNull(message = "songName must not be null")
        @NotEmpty(message = "songName must not be empty")
        String songName
) {
}
