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
//    private final UserConformer userConformer;

    @PostMapping("/register")
    ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserDto dto) {
        userDetailsManager.createUser(User.builder()
                .username(dto.username())
                .password(dto.password())
                .build());
        return ResponseEntity.ok(new RegisterUserResponseDto("Created User"));
    }

//    @GetMapping("/confirm")
//    public String confirm(@RequestParam String token) {
//        boolean isConfirmed = userConformer.confirmUser(token);
//        return isConfirmed ? "Registration confirmed!" : "Invalid confirmation token.";
//    }
}
