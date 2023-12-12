package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
class AlbumRetriever {

    private final AlbumRepository albumRepository;

    AlbumInfo findAlbumByIdWithArtistsAndSongs(final Long id) {
        return albumRepository.findAlbumByIdWithSongsAndArtists(id)
                .orElseThrow(() -> new AlbumNotFoundException("Album with id: " + id + " not found"));
    }

    Set<Album> findAlbumsByArtistId(final Long artistId) {
        return albumRepository.findAllAlbumsByArtistId(artistId);
    }

    Album findById(final Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(
                        () -> new AlbumNotFoundException("Album with id: " + albumId + " not found")
                );
    }
}
