package com.songify.domain.crud.song.query;

import com.songify.domain.crud.album.query.SimpleAlbumQueryDto;
import com.songify.domain.crud.genre.query.SimpleGenreQueryDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "song")
public class SimpleSongQueryDto {

    @Id
    private Long id;

    private String name;

    private UUID uuid;

    private Instant releaseDate;

    private Long duration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "album_id")
    private SimpleAlbumQueryDto album;

    @OneToOne(fetch = FetchType.LAZY)
    private SimpleGenreQueryDto genre;

    @Enumerated(EnumType.STRING)
    private SimpleSongLanguageQueryDto language;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleSongQueryDto that = (SimpleSongQueryDto) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
