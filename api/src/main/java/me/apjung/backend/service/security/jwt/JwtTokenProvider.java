package me.apjung.backend.service.security.jwt;

import me.apjung.backend.domain.user.User;

public interface JwtTokenProvider {
    String createToken(User user);
    Long getUserIdFromToken(String token);
    boolean verifyToken(String token);
}