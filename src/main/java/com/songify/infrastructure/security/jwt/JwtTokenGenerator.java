package com.songify.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.songify.infrastructure.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.*;

@AllArgsConstructor
@Component
public class JwtTokenGenerator {

    public static final String ROLES_CLAIM = "roles";

    private final AuthenticationManager authenticationManager;
    private final Clock clock;
    private final JwtConfigurationProperties properties;
    private final KeyPair keyPair;

    public String authenticateAndGenerateToken(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authentication);
        SecurityUser user = (SecurityUser) authenticate.getPrincipal();
        return createToken(user);
    }

    private String createToken(SecurityUser user) {
//        String secretKey = properties.secret(); symmetric key IMPL
//        Algorithm algorithm = Algorithm.HMAC256(secretKey); symmetric key IMPL
        Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) keyPair.getPrivate()); // asymmetric key
        long minutes = properties.expirationMinutes();
        String issuer = properties.issuer();
        Instant now = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expiresAt = now.plus(Duration.ofMinutes(minutes));
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withIssuer(issuer)
                .withClaim(ROLES_CLAIM, user.getAuthoritiesAsString())
                .sign(algorithm);
    }
}
