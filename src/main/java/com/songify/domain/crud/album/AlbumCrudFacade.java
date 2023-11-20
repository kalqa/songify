package com.songify.domain.crud.album;

import com.songify.domain.crud.album.query.SimpleAlbumQueryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AlbumCrudFacade {

    private final AlbumQueryRepository albumRepository;

    public SimpleAlbumQueryDto findAlbumById(final long albumId) {
        return albumRepository.findQueryDtoById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException(albumId));
    }

}
