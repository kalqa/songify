package com.songify.domain.crud.song.query;

import com.songify.domain.crud.album.query.SimpleAlbumQueryDto;
import com.songify.domain.crud.genre.query.SimpleGenreQueryDto;
import com.songify.domain.crud.util.BaseEntity;
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


@Entity
@NoArgsConstructor
@Table(name = "song")
@Getter
public class SimpleSongQueryDto extends BaseEntity {

    @Id
    private Long id;

    private String name;

    private Instant releaseDate;

    private Long duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private SimpleAlbumQueryDto album;

    @OneToOne(fetch = FetchType.LAZY)
    private SimpleGenreQueryDto genre;

    @Enumerated(EnumType.STRING)
    private SimpleSongLanguageQueryDto language;
}
