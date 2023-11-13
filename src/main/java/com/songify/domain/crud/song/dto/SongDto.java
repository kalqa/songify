package com.songify.domain.crud.song.dto;

import lombok.Builder;

@Builder
public record SongDto(
        Long id,
        String name
) {

}
