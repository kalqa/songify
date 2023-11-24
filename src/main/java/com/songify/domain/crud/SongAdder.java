package com.songify.domain.crud;

import com.songify.domain.crud.dto.SongDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.Instant;

@Log4j2
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongAdder {

    private final SongRepository songRepository;

    public SongDto addSong(final SongDto songDto) {
        String songLanguage = songDto.language();
        SongLanguage songLanguageDatabase = SongLanguage.valueOf(songLanguage);
        // some domain validator
        String name = songDto.name();
        Song vaidatedAndReadytoSaveSong = new Song(name);
        // some domain validator ended checking
        Song addedSong = addSong(vaidatedAndReadytoSaveSong, songLanguageDatabase);
        return SongDto.builder()
                .id(addedSong.getId())
                .name(addedSong.getName())
                .language(addedSong.getLanguage().name())
                .build();
    }

    Song addSong(final Song song, final SongLanguage songLanguageDatabase) {
        log.info("adding new song: " + song);
        song.setDuration(200L);
        song.setReleaseDate(Instant.now());
        song.setLanguage(songLanguageDatabase);
        return songRepository.save(song);
    }
}
