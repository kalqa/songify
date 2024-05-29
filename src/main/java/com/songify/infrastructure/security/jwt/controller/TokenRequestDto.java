package com.songify.infrastructure.security.jwt.controller;


import jakarta.validation.constraints.NotBlank;

record TokenRequestDto(
        @NotBlank(message = "{username.not.blank}")
        String username,

        @NotBlank(message = "{password.not.blank}")
        String password
) {
}
