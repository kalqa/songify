package com.songify.domain.crud.song.service;

import com.songify.domain.crud.album.Album;
import com.songify.domain.crud.song.model.Song;
import com.songify.domain.crud.song.repository.SongRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@Service
@AllArgsConstructor
class SongAdder {

    private final SongRepository songRepository;

    Song addSong(Song song) {
        log.info("adding new song: " + song);
        song.setAlbum(Album.builder()
                .id(2L)
                .build());
        song.setDuration(200L);
        song.setReleaseDate(Instant.now());
        return songRepository.save(song);
    }
}
