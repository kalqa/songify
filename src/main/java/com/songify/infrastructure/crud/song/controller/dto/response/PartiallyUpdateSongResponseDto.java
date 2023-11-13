package com.songify.infrastructure.crud.song.controller.dto.response;


import com.songify.domain.crud.song.dto.SongDto;

public record PartiallyUpdateSongResponseDto(SongDto updatedSong) {
}
