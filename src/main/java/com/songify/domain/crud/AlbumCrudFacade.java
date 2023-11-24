package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumDto;
import com.songify.domain.crud.exception.AlbumNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
class AlbumCrudFacade {

    private final AlbumRepository albumRepository;
    private final ArtistCrudFacade artistCrudFacade;

    public AlbumDto findDtoById(final long albumId) {
        return albumRepository.findById(albumId)
                .map(album -> new AlbumDto(album.getId()))
                .orElseThrow(() -> new AlbumNotFoundException(albumId));
    }

    AlbumDto addArtistToAlbum(final Long artistId, final Long albumId) {
        Artist artist = artistCrudFacade.findById(artistId);
        Album album = findById(albumId);
        album.addArtist(artist);
        return new AlbumDto(album.getId());
    }

    Album findById(final long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new AlbumNotFoundException(albumId));
    }

    Set<AlbumDto> findAll(final Pageable pageable) {
        return albumRepository.findAll(pageable)
                .stream()
                .map(album -> new AlbumDto(album.getId()))
                .collect(Collectors.toSet());
    }
}
