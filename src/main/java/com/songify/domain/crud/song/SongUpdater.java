package com.songify.domain.crud.song;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
class SongUpdater {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    SongUpdater(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }

    void updateById(Long id, Song newSong) {
        songRetriever.existsById(id);
        songRepository.updateById(id, newSong);
    }

    Song updatePartiallyById(Long id, Song songFromRequest) {
        Song songFromDatabase = songRetriever.findSongById(id);
        Song.SongBuilder builder = Song.builder();
        if (songFromRequest.getName() != null) {
            builder.name(songFromRequest.getName());
        } else {
            builder.name(songFromDatabase.getName());
        }
        if (songFromRequest.getArtist() != null) {
            builder.artist(songFromRequest.getArtist());
        } else {
            builder.artist(songFromDatabase.getArtist());
        }
        Song toSave = builder.build();
        updateById(id, toSave);
        return toSave;
    }

// Dirty checking version
//     void updateById(Long id, Song newSong) {
//        Song songById = songRetriever.findSongById(id);
//        songById.setName(newSong.getName());
//        songById.setArtist(newSong.getArtist());
//    }
//
//     Song updatePartiallyById(Long id, Song songFromRequest) {
//        Song songFromDatabase = songRetriever.findSongById(id);
//        if (songFromRequest.getName() != null) {
//            songFromDatabase.setName(songFromRequest.getName());
//        }
//        if (songFromRequest.getArtist() != null) {
//            songFromDatabase.setArtist(songFromRequest.getArtist());
//        }
//        return songFromDatabase;
//    }
}
