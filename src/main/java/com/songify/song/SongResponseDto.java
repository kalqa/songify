package com.songify.song;

import java.util.Map;

public record SongResponseDto(Map<Integer, String> songs) {
}
