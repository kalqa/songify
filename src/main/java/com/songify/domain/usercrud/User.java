package com.songify.domain.usercrud;

import com.songify.domain.crud.util.BaseEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

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

    private String confirmationToken;

    private boolean enabled = false;

    private Collection<String> authorities = new HashSet<>();

    public boolean confirm(){
        this.setEnabled(true);
        this.setConfirmationToken(null);
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public User(String email, String password, String confirmationToken, Collection<String> authorities) {
        this.email = email;
        this.password = password;
        this.confirmationToken = confirmationToken;
        this.authorities = authorities;
    }
}
