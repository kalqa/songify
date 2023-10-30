package com.songify.domain.crud.genre;

import com.songify.domain.crud.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends BaseEntity {

    @Id
    @GeneratedValue(generator = "genre_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "genre_id_seq",
            sequenceName = "genre_id_seq",
            allocationSize = 1
    )
    public Long id;

    private String name;

//    @OneToOne(mappedBy = "genre")
//    private Song song;

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
