package com.songify.infrastructure.usercrud.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
class UserCrudController {

    private final UserDetailsManager userDetailsManager;

    @PostMapping
    ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserDto registerUserDto) {
        userDetailsManager.createUser(User.builder()
                .username(registerUserDto.username())
                .password(registerUserDto.password())
                .roles("USER")
                .build());
        return ResponseEntity.ok(new RegisterUserResponseDto("Created User"));
    }


}
