package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.repository.SongRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class SongRetriever {

    private final SongRepository songRepository;

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

}
