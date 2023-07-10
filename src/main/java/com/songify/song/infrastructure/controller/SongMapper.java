package com.songify.song.infrastructure.controller;

import com.songify.song.domain.model.Artist;
import com.songify.song.domain.model.Song;
import com.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.response.*;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class SongMapper {

    public static SongDto mapFromSongToSongDto(Song song) {
        Set<ArtistDto> artistsSongs = song.getArtistsSongs()
                .stream().map(ArtistMapper::mapFromArtistToArtistDto).collect(Collectors.toSet());
        return new SongDto(song.getId(), song.getName(), artistsSongs);
    }

    public static Song mapFromCreateSongRequestDtoToSong(CreateSongRequestDto dto) {
        Artist artist = new Artist(dto.artist(), Collections.emptySet());
        Set<Artist> artistsSongs = Set.of(artist);
        return new Song(dto.songName(), artistsSongs);
    }

    public static Song mapFromUpdateSongRequestDtoToSong(UpdateSongRequestDto dto) {
        Artist artist = new Artist(dto.artist(), Collections.emptySet());
        Set<Artist> artistsSongs = Set.of(artist);
        return new Song(dto.songName(), artistsSongs);
    }

    public static Song mapFromPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateSongRequestDto dto) {
        Artist artist = new Artist(dto.artist(), Collections.emptySet());
        Set<Artist> artistsSongs = Set.of(artist);
        return new Song(dto.songName(), artistsSongs);
    }

    public static CreateSongResponseDto mapFromSongToCreateSongResponseDto(Song song) {
        return new CreateSongResponseDto(mapFromSongToSongDto(song));
    }

    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Long id) {
        return new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    public static UpdateSongResponseDto mapFromSongToUpdateSongResponseDto(Song newSong) {
        return new UpdateSongResponseDto(newSong.getName());
    }

    public static PartiallyUpdateSongResponseDto mapFromSongToPartiallyUpdateSongResponseDto(Song updatedSong) {
        return new PartiallyUpdateSongResponseDto(mapFromSongToSongDto(updatedSong));
    }

    public static GetSongResponseDto mapFromSongToGetSongResponseDto(Song song) {
        return new GetSongResponseDto(mapFromSongToSongDto(song));
    }

}
