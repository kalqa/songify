package com.songify.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    public static final int TOKEN_START_INDEX = 7;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String ROLES_CLAIM_NAME = "roles";

//    private final JwtConfigurationProperties properties;
    private final KeyPair keyPair;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (authorization == null) {
            filterChain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getUsernamePasswordAuthenticationToken(authorization);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) {
//        String secretKey = properties.secret(); symmetric key IMPL
//        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build(); // symmetric key IMPL
        JWTVerifier verifier = JWT.require(Algorithm.RSA256((RSAPublicKey) keyPair.getPublic(), null)).build(); // asymmetric key IMPL
        DecodedJWT jwt = verifier.verify(token.substring(TOKEN_START_INDEX));
        List<SimpleGrantedAuthority> authorities = jwt.getClaim(ROLES_CLAIM_NAME)
                .asList(String.class)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
        return new UsernamePasswordAuthenticationToken(jwt.getSubject(), null, authorities);
    }
}
