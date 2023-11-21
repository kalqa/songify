package com.songify.domain.crud.album.query;

import com.songify.domain.crud.artist.query.SimpleArtistQueryDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "album")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleAlbumQueryDto {

    @Id
    private Long id;

    private UUID uuid;

    private String title;

    private Instant releaseDate;

    @ManyToMany(mappedBy = "albums")
    private Set<SimpleArtistQueryDto> artist;

    private Set<Long> songIds;

    Set<SimpleArtistQueryDto> getArtist() {
        return Collections.unmodifiableSet(artist);
    }

    Set<Long> getSongIds() {
        return songIds;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SimpleAlbumQueryDto that = (SimpleAlbumQueryDto) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
