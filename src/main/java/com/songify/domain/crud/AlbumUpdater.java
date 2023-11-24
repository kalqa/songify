package com.songify.domain.crud;

import com.songify.domain.crud.dto.AlbumDto;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional
class AlbumUpdater {

    private final AlbumRetriever albumRetriever;
    private final ArtistRetriever artistRetriever;

    AlbumDto addArtistToAlbum(final Long artistId, final Long albumId) {
        Artist artist = artistRetriever.findById(artistId);
        Album album = albumRetriever.findById(albumId);
        album.addArtist(artist);
        return new AlbumDto(album.getId());
    }
}
