package com.songify.song.infrastructure.controller;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class SongUpdater {

    private final SongRepository songRepository;

    SongUpdater(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void updateById(Long id, Song newSong) {
        songRepository.updateById(id, newSong);
        log.info("Updated song with id: " + id);
    }

//    public void patchById(Song songFromDatabase, Song updatedSong) {
//        Song.SongBuilder builder = Song.builder();
//        if (updatedSong.getName() != null) {
//            builder.name(updatedSong.getName());
//        } else {
//            builder.name(songFromDatabase.getName());
//        }
//        if (updatedSong.getArtistsSongs() != null) {
//            builder.artist(updatedSong.getArtist());
//        } else {
//            builder.artist(songFromDatabase.getArtist());
//        }
//        updateById(songFromDatabase.getId(), builder.build());
//    }
}
