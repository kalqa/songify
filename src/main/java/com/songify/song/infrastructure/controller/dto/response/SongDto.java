package com.songify.song.infrastructure.controller.dto.response;

import java.util.Set;

public record SongDto(Long id, String name, Set<ArtistDto> artists) {

}
