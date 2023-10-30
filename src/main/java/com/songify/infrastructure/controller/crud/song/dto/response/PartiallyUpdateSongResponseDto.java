package com.songify.infrastructure.controller.crud.song.dto.response;


import com.songify.domain.crud.dto.SongDto;

public record PartiallyUpdateSongResponseDto(SongDto updatedSong) {
}
