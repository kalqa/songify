package com.songify.domain.crud;

import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@AllArgsConstructor
@NoArgsConstructor
class Genre extends BaseEntity {

    @Id
    @GeneratedValue(generator = "genre_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "genre_id_seq",
            sequenceName = "genre_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String name;

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    Genre(final Long id) {
        this.id = id;
    }
}
