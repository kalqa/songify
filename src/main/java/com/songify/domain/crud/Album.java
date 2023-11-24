package com.songify.domain.crud;


import com.songify.domain.crud.dto.ArtistDto;
import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.CascadeType;
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
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany
    private Set<Song> songs = new HashSet<>();

    @ManyToMany(mappedBy = "albums")
    private Set<Artist> artists = new HashSet<>();

//    Set<ArtistDto> getArtistsDto() {
//        return artists.stream().map(
//                artist -> new ArtistDto(artist.getId())
//        ).collect(Collectors.toSet());
//    }


    //    void addSong(Long songId) {
//        songIds.add(songId);
//    }


    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", uuid=" + uuid +
                ", createdOn=" + createdOn +
                ", version=" + version +
                '}';
    }

    void addArtist(final Artist artist) {
        if (!artists.contains(artist)) {
            artists.add(artist);
            artist.addAlbum(this);
        }
    }
}
