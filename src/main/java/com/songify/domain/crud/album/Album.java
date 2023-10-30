package com.songify.domain.crud.album;


import com.songify.domain.crud.artist.Artist;
import com.songify.domain.crud.BaseEntity;
import com.songify.domain.crud.song.model.Song;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Builder
@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album extends BaseEntity {

    @Id
    @GeneratedValue(generator = "album_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "album_id_seq",
            sequenceName = "album_id_seq",
            allocationSize = 1
    )
    public Long id;

    private String title;

    private Instant releaseDate;

    @ManyToMany(mappedBy = "albums")
    private Set<Artist> artist;

    @OneToMany(mappedBy = "album")
    private Set<Song> songs;

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
