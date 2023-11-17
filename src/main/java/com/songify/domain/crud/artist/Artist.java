package com.songify.domain.crud.artist;

import com.songify.domain.crud.util.BaseEntity;
import com.songify.domain.crud.album.query.SimpleAlbumQueryDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
@NoArgsConstructor
class Artist extends BaseEntity {

    @Id
    @GeneratedValue(generator = "artist_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "artist_id_seq",
            sequenceName = "artist_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "album_id")}
    )
    private Set<SimpleAlbumQueryDto> albums;

    public Artist(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }

    void addAlbum(SimpleAlbumQueryDto album) {
        this.albums.add(album);
    }
}


