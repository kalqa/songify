package com.songify.infrastructure.security.jwt.controller;

import lombok.Builder;

@Builder
record JwtResponseDto(
        String token
) {
}
