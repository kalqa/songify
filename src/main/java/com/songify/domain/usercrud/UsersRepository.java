package com.songify.domain.usercrud;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UsersRepository extends Repository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    Optional<User> findByConfirmationToken(String confirmationToken);

    User save(User user);

    boolean existsByEmail(String id);
}
