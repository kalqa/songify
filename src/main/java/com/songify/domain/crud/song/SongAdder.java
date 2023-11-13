package com.songify.domain.crud.song;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@Service
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongAdder {

    private final SongRepository songRepository;

    Song addSong(final Song song) {
        log.info("adding new song: " + song);
        song.setDuration(200L);
        song.setReleaseDate(Instant.now());
        return songRepository.save(song);
    }
}
