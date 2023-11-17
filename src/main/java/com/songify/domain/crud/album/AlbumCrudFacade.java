package com.songify.domain.crud.album;

import com.songify.domain.crud.album.dto.AlbumInfo;
import com.songify.domain.crud.album.query.SimpleAlbumQueryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlbumCrudFacade {

    private final AlbumRepository albumRepository;

    public AlbumInfo findAlbumById(final long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException(albumId));
    }

    AlbumInfo addSongToAlbum(final Long albumId, final Long songId) {
        return null;
    }
}
