package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.repository.SongRepository;
import com.songify.song.domain.repository.SongRepositoryJdbcTemplateImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class SongRetriever {

    private final SongRepository songRepository;
    private final SongRepositoryJdbcTemplateImpl template;

    public List<Song> findAll(Pageable pageable) {
        log.info("retrieving all songs: ");
        return template.findAll(pageable);
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
