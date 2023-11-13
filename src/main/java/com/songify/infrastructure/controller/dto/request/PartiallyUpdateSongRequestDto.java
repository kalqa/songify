package com.songify.infrastructure.controller.dto.request;

public record PartiallyUpdateSongRequestDto(
        String songName,
        String artist
) {
}
