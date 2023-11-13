package com.songify.infrastructure.controller;

import com.songify.domain.crud.song.Song;
import com.songify.domain.crud.song.SongAdder;
import com.songify.domain.crud.song.SongCrudFacade;
import com.songify.domain.crud.song.SongDeleter;
import com.songify.domain.crud.song.SongRetriever;
import com.songify.domain.crud.song.SongUpdater;
import com.songify.infrastructure.controller.dto.request.CreateSongRequestDto;
import com.songify.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.infrastructure.controller.dto.response.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.songify.infrastructure.controller.SongMapper.*;

@RestController
@Log4j2
@RequestMapping("/songs")
@AllArgsConstructor
public class SongRestController {

    private final SongCrudFacade songFacade;

    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<Song> allSongs = songFacade.findAll(pageable);
        GetAllSongsResponseDto response = mapFromSongToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSongResponseDto> getSongById(@PathVariable Long id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        Song song = songFacade.findSongById(id);
        GetSongResponseDto response = mapFromSongToGetSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request) {
        Song song = mapFromCreateSongRequestDtoToSong(request);
        Song savedSong = songFacade.addSong(song);
        CreateSongResponseDto body = mapFromSongToCreateSongResponseDto(savedSong);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/test")
    public ResponseEntity<GetSongResponseDto> test() {
        songFacade.compareSongs();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Long id) {
        songFacade.deleteById(id);
        DeleteSongResponseDto body = mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> update(@PathVariable Long id,
                                                        @RequestBody @Valid UpdateSongRequestDto request) {
        Song newSong = mapFromUpdateSongRequestDtoToSong(request);
        songFacade.updateById(id, newSong);
        UpdateSongResponseDto body = mapFromSongToUpdateSongResponseDto(newSong);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Long id,
                                                                              @RequestBody PartiallyUpdateSongRequestDto request) {
        Song updatedSong = mapFromPartiallyUpdateSongRequestDtoToSong(request);
        Song savedSong = songFacade.updatePartiallyById(id, updatedSong);
        PartiallyUpdateSongResponseDto body = mapFromSongToPartiallyUpdateSongResponseDto(savedSong);
        return ResponseEntity.ok(body);
    }
}
