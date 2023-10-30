package com.songify.infrastructure.controller.crud.song.dto.request;

public record PartiallyUpdateSongRequestDto(
        String songName,
        String artist
) {
}
