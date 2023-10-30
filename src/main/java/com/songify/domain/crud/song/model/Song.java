package com.songify.domain.crud.song.model;

import com.songify.domain.crud.BaseEntity;
import com.songify.domain.crud.genre.Genre;
import com.songify.domain.crud.album.Album;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song extends BaseEntity {

    @Id
    @GeneratedValue(generator = "song_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "song_id_seq",
            sequenceName = "song_id_seq",
            allocationSize = 1
    )
    public Long id;

    @Column(nullable = false)
    String name;

    Instant releaseDate;

    private Long duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @OneToOne(fetch = FetchType.LAZY)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private SongLanguage language;

    public Song(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
                ", album=" + album +
                ", genre=" + genre +
                '}';
    }
}
