package com.songify.song.infrastructure.controller;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.service.SongAdder;
import com.songify.song.domain.service.SongRetriever;
import com.songify.song.infrastructure.controller.dto.request.CreateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.PartiallyUpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.request.UpdateSongRequestDto;
import com.songify.song.infrastructure.controller.dto.response.CreateSongResponseDto;
import com.songify.song.infrastructure.controller.dto.response.DeleteSongResponseDto;
import com.songify.song.infrastructure.controller.dto.response.GetAllSongsResponseDto;
import com.songify.song.infrastructure.controller.dto.response.GetSongResponseDto;
import com.songify.song.infrastructure.controller.dto.response.PartiallyUpdateSongResponseDto;
import com.songify.song.infrastructure.controller.dto.response.UpdateSongResponseDto;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Log4j2
@RequestMapping("/songs")
public class SongRestController {

    private final SongAdder songAdder;
    private final SongRetriever songRetriever;
    private final ArtistSaver artistSaver;

    SongRestController(SongAdder songAdder, SongRetriever songRetriever, ArtistSaver artistSaver) {
        this.songAdder = songAdder;
        this.songRetriever = songRetriever;
        this.artistSaver = artistSaver;
    }

    @GetMapping
    public ResponseEntity<GetAllSongsResponseDto> getAllSongs(@RequestParam(required = false) Integer limit) {
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if (limit != null) {
            Map<Integer, Song> limitedMap = songRetriever.findAllLimitedBy(limit);
            GetAllSongsResponseDto response = new GetAllSongsResponseDto(limitedMap);
            return ResponseEntity.ok(response);
        }
        GetAllSongsResponseDto response = SongMapper.mapFromSongToGetAllSongsResponseDto(allSongs);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSongResponseDto> getSongById(@PathVariable Integer id, @RequestHeader(required = false) String requestId) {
        log.info(requestId);
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if (!allSongs.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song song = allSongs.get(id);
        GetSongResponseDto response = SongMapper.mapFromSongToGetSongResponseDto(song);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateSongResponseDto> postSong(@RequestBody @Valid CreateSongRequestDto request) {
        Song song = SongMapper.mapFromCreateSongRequestDtoToSong(request);


        artistSaver.addArtist(song.artist());
        artistSaver.printArtists();
        artistSaver.printArtistsSize();
        artistSaver.printSaverName();

        songAdder.addSong(song);
        CreateSongResponseDto body = SongMapper.mapFromSongToCreateSongResponseDto(song);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteSongResponseDto> deleteSongByIdUsingPathVariable(@PathVariable Integer id) {
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if (!allSongs.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        allSongs.remove(id);
        DeleteSongResponseDto body = SongMapper.mapFromSongToDeleteSongResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSongResponseDto> update(@PathVariable Integer id,
                                                        @RequestBody @Valid UpdateSongRequestDto request) {
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if (!allSongs.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song newSong = SongMapper.mapFromUpdateSongRequestDtoToSong(request);
        Song oldSong = allSongs.put(id, newSong);
        log.info("Updated song with id: " + id +
                " with oldSongName: " + oldSong.name() + " to newSongName: " + newSong.name() +
                " oldArtist: " + oldSong.artist() + " to newArtist: " + newSong.artist());
        UpdateSongResponseDto body = SongMapper.mapFromSongToUpdateSongResponseDto(newSong);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateSongResponseDto> partiallyUpdateSong(@PathVariable Integer id,
                                                                              @RequestBody PartiallyUpdateSongRequestDto request) {
        Map<Integer, Song> allSongs = songRetriever.findAll();
        if (!allSongs.containsKey(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
        Song songFromDatabase = allSongs.get(id);
        Song updatedSong = SongMapper.mapFromPartiallyUpdateSongRequestDtoToSong(request);
        Song.SongBuilder builder = Song.builder();
        if (updatedSong.name() != null) {
            builder.name(updatedSong.name());
        } else {
            builder.name(songFromDatabase.name());
        }
        if (updatedSong.artist() != null) {
            builder.artist(updatedSong.artist());
        } else {
            builder.artist(songFromDatabase.artist());
        }
        allSongs.put(id, updatedSong);
        PartiallyUpdateSongResponseDto body = SongMapper.mapFromSongToPartiallyUpdateSongResponseDto(updatedSong);
        return ResponseEntity.ok(body);
    }
}
