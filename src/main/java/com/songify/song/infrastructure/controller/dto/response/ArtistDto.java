package com.songify.song.infrastructure.controller.dto.response;

import java.util.Set;

public record ArtistDto(Long id, String nam, Set<SongDto> songs) {
}
