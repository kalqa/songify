package com.songify.infrastructure.controller.crud.song.dto.response;

import com.songify.domain.crud.dto.SongDto;

import java.util.List;

public record GetAllSongsResponseDto(List<SongDto> songs) {
}
