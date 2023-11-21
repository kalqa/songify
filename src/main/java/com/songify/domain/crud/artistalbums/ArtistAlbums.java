package com.songify.domain.crud.artistalbums;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "artist_albums")
class ArtistAlbums {

    @EmbeddedId
    private AlbumArtistId id;

    Long getArtistId() {
        return id.getArtistId();
    }

    Long getAlbumId() {
        return id.getAlbumId();
    }
}


