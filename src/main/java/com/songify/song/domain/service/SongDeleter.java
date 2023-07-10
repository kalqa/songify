package com.songify.song.domain.service;

import com.songify.song.domain.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class SongDeleter {

    private final SongRepository songRepository;

    SongDeleter(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }
}
