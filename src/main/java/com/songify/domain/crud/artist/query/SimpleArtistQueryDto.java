package com.songify.domain.crud.artist.query;

import com.songify.domain.crud.album.query.SimpleAlbumQueryDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "artist")
public class SimpleArtistQueryDto {

    @Id
    private Long id;

    private UUID uuid;

    private String name;

    private Instant releaseDate;

    @ManyToMany
    @JoinTable(
            joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "album_id")}
    )
    private Set<SimpleAlbumQueryDto> albums;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleArtistQueryDto that = (SimpleArtistQueryDto) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

}
