package com.songify.infrastructure.security.jwt.controller;

import com.songify.infrastructure.security.jwt.JwtTokenGenerator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/token")
class JwtTokenController {

    private final JwtTokenGenerator tokenGenerator;

    @PostMapping
    public ResponseEntity<JwtResponseDto> authenticateAndGenerateToken(@Valid @RequestBody TokenRequestDto dto) {
        final String token = tokenGenerator.authenticateAndGenerateToken(dto.username(), dto.password());
        return ResponseEntity.ok(JwtResponseDto.builder()
                .token(token)
                .build());
    }

}
