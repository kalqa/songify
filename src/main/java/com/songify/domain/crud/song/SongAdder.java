package com.songify.domain.crud.song;

import com.songify.domain.crud.album.query.SimpleAlbumQueryDto;
import com.songify.domain.crud.genre.query.SimpleGenreQueryDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@Service
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongAdder {

    private final SongRepository songRepository;

    Song addSong(final Song song, final SimpleAlbumQueryDto album, final SimpleGenreQueryDto genre, final SongLanguage songLanguageDatabase) {
        log.info("adding new song: " + song);
        song.setDuration(200L);
        song.setReleaseDate(Instant.now());
        song.setAlbum(album);
        song.setGenre(genre);
        song.setLanguage(songLanguageDatabase);
        return songRepository.save(song);
    }
}
