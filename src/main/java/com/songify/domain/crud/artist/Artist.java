package com.songify.domain.crud.artist;

import com.songify.domain.crud.BaseEntity;
import com.songify.domain.crud.album.Album;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Artist extends BaseEntity {

    @Id
    @GeneratedValue(generator = "artist_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "artist_id_seq",
            sequenceName = "artist_id_seq",
            allocationSize = 1
    )
    public Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "album_id")}
    )
    private Set<Album> albums;

    public Artist(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }
}
