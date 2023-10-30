package com.songify.infrastructure.controller.crud.song;


import com.songify.domain.crud.dto.SongDto;
import com.songify.infrastructure.controller.crud.song.dto.request.CreateSongRequestDto;
import com.songify.infrastructure.controller.crud.song.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.infrastructure.controller.crud.song.dto.request.UpdateSongRequestDto;
import com.songify.infrastructure.controller.crud.song.dto.response.*;
import org.springframework.http.HttpStatus;

import java.util.List;

public class SongControllerMapper {

    public static SongDto mapFromCreateSongRequestDtoToSongDto(CreateSongRequestDto dto) {
        return SongDto
                .builder()
                .name(dto.songName())
                .build();
    }

    public static SongDto mapFromUpdateSongRequestDtoToSongDto(UpdateSongRequestDto dto) {
        return SongDto
                .builder()
                .name(dto.songName())
                .build();
    }

    public static SongDto mapFromPartiallyUpdateSongRequestDtoToSong(PartiallyUpdateSongRequestDto dto) {
        return SongDto
                .builder()
                .name(dto.songName())
                .build();
    }

    public static CreateSongResponseDto mapFromSongToCreateSongResponseDto(SongDto songDto) {
        return new CreateSongResponseDto(songDto);
    }

    public static DeleteSongResponseDto mapFromSongToDeleteSongResponseDto(Long id) {
        return new DeleteSongResponseDto("You deleted song with id: " + id, HttpStatus.OK);
    }

    public static UpdateSongResponseDto mapFromSongToUpdateSongResponseDto(SongDto newSong) {
        return new UpdateSongResponseDto(newSong.name(), "testt");
    }

    public static PartiallyUpdateSongResponseDto mapFromSongDtoToPartiallyUpdateSongResponseDto(SongDto songDto) {
        return new PartiallyUpdateSongResponseDto(songDto);
    }

    public static GetSongResponseDto mapFromSongToGetSongResponseDto(SongDto songDto) {
        return new GetSongResponseDto(songDto);
    }

    public static GetAllSongsResponseDto mapFromSongToGetAllSongsResponseDto(List<SongDto> songs) {
        return new GetAllSongsResponseDto(songs);
    }
}
