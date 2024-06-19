package com.songify.infrastructure.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class JwtTokenController {

    private final JwtTokenGenerator tokenGenerator;

    @PostMapping("/token")
    public ResponseEntity<JwtResponseDto> authenticateAndGenerateToken(@RequestBody TokenRequestDto dto){
        String token = tokenGenerator.authenticateAndGenerateToken(dto.username(), dto.password());
        return ResponseEntity.ok(
                JwtResponseDto.builder()
                        .token(token)
                        .build()
        );
    }
}
