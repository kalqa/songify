package com.songify.domain.crud.album;

import com.songify.domain.crud.album.dto.AlbumInfo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@Log4j2
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
class AlbumRetriever {

    private final AlbumRepository albumRepository;

    public AlbumInfo findAlbumById(Long id) {
        log.info("finding album by id: " + id);
        return albumRepository.findById(id).orElseThrow(() -> new RuntimeException("album not found"));
    }
}
