package com.songify.domain.crud.genre.query;

import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Table(name = "genre")
@AllArgsConstructor
@Getter
@Builder
public class SimpleGenreQueryDto extends BaseEntity {

    @Id
    private Long id;

    private String name;
}
