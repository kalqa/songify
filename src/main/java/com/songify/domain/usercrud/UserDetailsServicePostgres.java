package com.songify.domain.usercrud;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Transactional
class UserDetailsServicePostgres implements UserDetailsManager {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConformer userConformer;

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
                user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );
        usersRepository.save(createdUser);
        System.out.println("saved user");
        userConformer.sendConfirmationEmail(createdUser);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return usersRepository.existsByEmail(username);
    }
}
