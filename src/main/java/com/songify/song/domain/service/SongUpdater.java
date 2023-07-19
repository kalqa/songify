package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.repository.SongRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@AllArgsConstructor
public class SongUpdater {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;
    private final SongAdder songAdder;


    public void updateById(Long id, Song newSong) {
        songRetriever.existsById(id);
        songRepository.updateById(id, newSong);
    }

    public void someComplicatedLogic() {
        songRepository.updateById(1L, new Song("siema", "eniu"));
        songRepository.updateById(2L, new Song("siema", "eniu"));
        if (true) {
            throw new RuntimeException();
        }
        songRepository.updateById(3L, new Song("siema", "eniu"));

        Song piesSong = songAdder.addSong(new Song("pies", "pies"));
        songRepository.updateById(piesSong.getId(), new Song("pies2", "pies2"));

    }

//    public void transfer() {
//        // from Bartek To Ania
//        songRepository.updateById(1L, new Song("2000", "bartek"));
//        songRepository.updateById(1L, new Song("1500", "bartek"));
//        // to
//        songRepository.updateById(2L, new Song("2000", "ania"));
//        songRepository.updateById(2L, new Song("2500", "ania"));
//    }

    public Song updatePartiallyById(Long id, Song songFromRequest) {
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
//    public void updateById(Long id, Song newSong) {
//        Song songById = songRetriever.findSongById(id);
//        songById.setName(newSong.getName());
//        songById.setArtist(newSong.getArtist());
//    }
//
//    public Song updatePartiallyById(Long id, Song songFromRequest) {
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
