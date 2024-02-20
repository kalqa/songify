package com.songify.domain.crud.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AlbumRequestDto(
        String title,
        Instant releaseDate,
        Long songId
) {
}
