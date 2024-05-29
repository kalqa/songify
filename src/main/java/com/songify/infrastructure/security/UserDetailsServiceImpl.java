package com.songify.infrastructure.security;

import com.songify.domain.usercrud.User;
import com.songify.domain.usercrud.UsersRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
@Transactional
class UserDetailsServiceImpl implements UserDetailsManager {

    public static final String DEFAULT_USER_ROLE = "ROLE_USER";

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
//    private final UserConformer userConformer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findFirstByEmail(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public void createUser(UserDetails user) {
        if (userExists(user.getUsername())) {
            System.out.println("not saved user - already exists");
            throw new RuntimeException("not saved user - already exists");
        }
        User createdUser = new User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                UUID.randomUUID().toString(),
                List.of(DEFAULT_USER_ROLE)
        );
        usersRepository.save(createdUser);
        System.out.println("saved user");
//        userConformer.sendConfirmationEmail(createdUser);
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new NotImplementedException("TODO");
    }

    @Override
    public void deleteUser(String username) {
        throw new NotImplementedException("TODO");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new NotImplementedException("TODO");
    }

    @Override
    public boolean userExists(String username) {
        return usersRepository.existsByEmail(username);
    }
}
