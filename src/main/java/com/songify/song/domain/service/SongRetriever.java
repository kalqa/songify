package com.songify.song.domain.service;

import com.songify.song.domain.model.Song;
import com.songify.song.domain.model.SongNotFoundException;
import com.songify.song.domain.repository.SongRepository;
import com.songify.song.infrastructure.controller.SongMapper;
import com.songify.song.infrastructure.controller.dto.response.SongDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class SongRetriever {

    private final SongRepository songRepository;

    SongRetriever(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<SongDto> findAllPageable(Pageable pageable) {
        log.info("retrieving all songs: ");
        return songRepository.findAll(pageable)
                .stream()
                .map(SongMapper::mapFromSongToSongDto)
                .toList();
    }

    public void existsById(Long id) {
        findById(id).orElseThrow(() -> new SongNotFoundException("Song with id " + id + " not found"));
    }

    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }
}
