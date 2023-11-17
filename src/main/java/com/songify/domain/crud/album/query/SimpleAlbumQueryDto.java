package com.songify.domain.crud.album.query;

import com.songify.domain.crud.artist.query.SimpleArtistQueryDto;
import com.songify.domain.crud.song.query.SimpleSongQueryDto;
import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "album")
@AllArgsConstructor
@Builder
public class SimpleAlbumQueryDto extends BaseEntity {

    @Id
    @GeneratedValue(generator = "album_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "album_id_seq",
            sequenceName = "album_id_seq",
            allocationSize = 1
    )
    private Long id;

    @NotNull
    private String title;

    private Instant releaseDate;

    private Set<SimpleArtistQueryDto> artists;

    private Set<SimpleSongQueryDto> songs;

    Set<SimpleSongQueryDto> getSongs() {
        return Collections.unmodifiableSet(songs);
    }

    Set<SimpleArtistQueryDto> getArtists() {
        return Collections.unmodifiableSet(artists);
    }

    public SimpleAlbumQueryDto(@NotNull final String title) {
        this.title = title;
    }

}
