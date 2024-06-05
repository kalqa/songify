package com.songify.infrastructure.security;

import com.songify.domain.usercrud.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

@AllArgsConstructor
class UserDetailsServiceImpl implements UserDetailsManager {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByEmail(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new RuntimeException("not found user"));
    }

    @Override
    public void createUser(UserDetails user) {
//        userRepository.save()
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
        return userRepository.existsByEmail(username);
    }
}
