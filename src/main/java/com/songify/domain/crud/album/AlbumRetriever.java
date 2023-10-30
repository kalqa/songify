package com.songify.domain.crud.album;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class AlbumRetriever {

    private final AlbumRepository albumRepository;

    AlbumRetriever(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album findAlbumById(Long id) {
        log.info("finding album by id: " + id);
        return albumRepository.findById(id).orElseThrow(() -> new RuntimeException("album not found"));
    }
}
