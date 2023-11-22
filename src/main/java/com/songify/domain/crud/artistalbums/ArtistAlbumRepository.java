package com.songify.domain.crud.artistalbums;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.Set;

interface ArtistAlbumRepository extends Repository<ArtistAlbums, AlbumArtistId> {


    void deleteById_AlbumIdAndId_ArtistId(Long albumId, Long artistId);

    Optional<ArtistAlbums> findById(AlbumArtistId id);

    Set<ArtistAlbums> findByIdAlbumId(Long albumId);

    void save(ArtistAlbums albums);
}
