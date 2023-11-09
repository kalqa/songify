package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class SongRetriever {

    private final SongRepository songRepository;
    final List<Song> songs = new ArrayList<>();

    SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll(Pageable pageable) {
        log.info("retrieving all songs: ");
        return songRepository.findAll(pageable);
    }

    public Song findSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException("Song with id " + id + " not found"));
    }

    public void existsById(Long id) {
        if (!songRepository.existsById(id)) {
            throw new SongNotFoundException("Song with id " + id + " not found");
        }
    }

    public Song compareSongs() {
        Song song1 = songRepository.findById(3L)
                .orElseThrow(() -> new SongNotFoundException("Song with id " + 3L + " not found"));

        Song song2 = songRepository.findById(4L)
                .orElseThrow(() -> new SongNotFoundException("Song with id " + 4L + " not found"));

        log.info(song1);
        log.info(song2);
        songs.add(song1);
        songs.add(song2);

        log.info(songs.get(0).equals(songs.get(1)));


        return song1;
    }

}
