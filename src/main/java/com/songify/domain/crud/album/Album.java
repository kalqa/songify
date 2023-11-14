package com.songify.domain.crud.album;

import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@NoArgsConstructor
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
class Album extends BaseEntity {

    @Id
    @GeneratedValue(generator = "album_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "album_id_seq",
            sequenceName = "album_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String title;

    private Instant releaseDate;
}
