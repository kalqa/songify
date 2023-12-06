package com.songify.domain.crud.dto;

import java.time.Instant;

public record AlbumRequestDto(
        String title,
        Instant releaseDate,
        Long songId
) {
}
