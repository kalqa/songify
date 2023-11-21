package com.songify.domain.crud.artistalbums;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
class AlbumArtistId implements Serializable {

    @Column(name = "album_id")
    private Long albumId;

    @Column(name = "artist_id")
    private Long artistId;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AlbumArtistId that = (AlbumArtistId) o;
        return Objects.equals(albumId, that.albumId) && Objects.equals(artistId, that.artistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, artistId);
    }
}
