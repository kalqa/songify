package com.songify.domain.crud.song;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
class SongDeleter {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    SongDeleter(SongRepository songRepository, SongRetriever songRetriever) {
        this.songRepository = songRepository;
        this.songRetriever = songRetriever;
    }

    void deleteById(Long id) {
        songRetriever.existsById(id);
        log.info("deleting song by id: " + id);
        songRepository.deleteById(id);
    }
}
