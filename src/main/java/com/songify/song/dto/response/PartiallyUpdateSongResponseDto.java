package com.songify.song.dto.response;

import com.songify.song.controller.Song;

public record PartiallyUpdateSongResponseDto(Song updatedSong) {
}
