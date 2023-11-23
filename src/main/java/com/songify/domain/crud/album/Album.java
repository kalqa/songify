package com.songify.domain.crud.album;


import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Set;

@Entity
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Album extends BaseEntity {

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

    @ElementCollection
    @CollectionTable(name = "song", joinColumns = @JoinColumn(name = "album_id"))
    @Column(name = "id")
    private Set<Long> songIds;

    @ElementCollection
    @CollectionTable(name = "artist_albums", joinColumns = @JoinColumn(name = "album_id"))
    @Column(name = "artist_id")
    private Set<Long> artistIds;

//    void addSong(Long songId) {
//        songIds.add(songId);
//    }

//    @Override
//    public String toString() {
//        return "Album{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", releaseDate=" + releaseDate +
//                '}';
//    }

}
