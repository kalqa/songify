package com.songify.domain.crud.album;


import com.songify.domain.crud.util.BaseEntity;
import com.songify.domain.crud.artist.query.SimpleArtistQueryDto;
import com.songify.domain.crud.song.query.SimpleSongQueryDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Entity
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
@NoArgsConstructor
class Album extends BaseEntity {

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

    @ManyToMany(mappedBy = "albums")
    private Set<SimpleArtistQueryDto> artist;

    @OneToMany(mappedBy = "album")
    private Set<SimpleSongQueryDto> songs;

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }

    void addArtistToAlbum(SimpleArtistQueryDto artist) {
        this.artist.add(artist);
    }

    void addSongToAlbum(SimpleSongQueryDto song) {
        this.songs.add(song);
    }

}
