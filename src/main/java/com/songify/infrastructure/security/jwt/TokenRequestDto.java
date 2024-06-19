package com.songify.infrastructure.security.jwt;

public record TokenRequestDto(String username, String password) {
}
