package com.songify.domain.crud.album;

import com.songify.domain.crud.album.dto.AlbumDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AlbumCrudFacade {

    private final AlbumRepository albumRepository;

    public AlbumDto findAlbumById(final long albumId) {
        return albumRepository.findById(albumId)
                .map(album -> new AlbumDto(album.getId()))
                .orElseThrow(() -> new AlbumNotFoundException(albumId));
    }

}
