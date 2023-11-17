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

    Song addSong(final Song song, final Long albumId, final Long genreId) {
        log.info("adding new song: " + song);
        song.setDuration(200L);
        song.setReleaseDate(Instant.now());
        SimpleAlbumQueryDto album = new SimpleAlbumQueryDto("some title");
        song.setAlbum(album);
//        song.setGenre(SimpleGenreQueryDto.builder().build());
        return songRepository.save(song);
    }
}
