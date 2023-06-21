package com.songify.song.dto;

import java.util.Map;

public record SongResponseDto(Map<Integer, String> songs) {
}
