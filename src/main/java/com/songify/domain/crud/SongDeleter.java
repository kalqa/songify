package com.songify.domain.crud;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class SongDeleter {

    private final SongRepository songRepository;
    private final SongRetriever songRetriever;

    public void deleteById(Long id) {
        songRetriever.existsById(id);
        log.info("deleting song by id: " + id);
        songRepository.deleteById(id);
    }
}
