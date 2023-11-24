package com.songify.domain.crud.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record SongDto(Long id, String name, Long genreId, String language) {
}
