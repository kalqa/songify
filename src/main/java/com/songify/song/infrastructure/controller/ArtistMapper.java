package com.songify.song.infrastructure.controller;

import com.songify.song.domain.model.Artist;
import com.songify.song.infrastructure.controller.dto.response.ArtistDto;

import java.util.stream.Collectors;

public class ArtistMapper {

    public static ArtistDto mapFromArtistToArtistDto(Artist artist) {
        return new ArtistDto(artist.getId(), artist.getName(), artist.getSongs()
                .stream()
                .map(SongMapper::mapFromSongToSongDto).collect(Collectors.toSet()));
    }

}
