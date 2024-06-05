package com.songify.domain.usercrud;

import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
class User extends BaseEntity {

    @Id
    @GeneratedValue(generator = "users_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "users_id_seq",
            sequenceName = "users_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private boolean enabled = true;

    private Collection<String> authorities = new HashSet<>();

    public User(String email, String password, boolean enabled, Collection<String> authorities) {
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }
}
