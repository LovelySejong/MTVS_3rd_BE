package com.mtvs.sejong._core.jwt;

import com.mtvs.sejong.user.dto.UserResponseDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JWTTokenProvider {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static final String TYPE_ACCESS = "access";
    private static final String TYPE_REFRESH = "refresh";
    private static final String CLAIM_TYPE = "type";

    // jwt 토큰 암호화를 위한 키
    private final Key secretKey;
    // Access token의 시간 : 3시간
    private static final long ACCESS_TOKEN_LIFETIME = 3 * 60 * 60 * 1000L;

    public JWTTokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public UserResponseDTO.authTokenDTO generateToken(Authentication authentication) {
        return generateToken(authentication.getName(), authentication.getAuthorities());
    }

    public UserResponseDTO.authTokenDTO generateToken(String name, Collection<? extends GrantedAuthority> grantedAuthorities) {
        // 권한 확인
        String authorities = grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // 현재 시간
        Date now = new Date();

        // Access 토큰 제작
        String accessToken = Jwts.builder()
                // 아이디 주입
                .setSubject(name)
                // 권한 주입
                .claim(AUTHORITIES_KEY, authorities)
                .claim(CLAIM_TYPE, TYPE_ACCESS)
                // 토큰 발행 시간 정보
                .setIssuedAt(now)
                // 만료시간 주입
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_LIFETIME))
                // 암호화
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();


        return new UserResponseDTO.authTokenDTO(BEARER_TYPE, accessToken, ACCESS_TOKEN_LIFETIME);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.info("올바르지 않은 서명의 JWT Token 입니다.", e);
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT Token 입니다.", e);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 형식의 JWT Token 입니다.", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT Claims가 비어있습니다.", e);
        }
        return false;
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT Token 입니다.", e);
            return e.getClaims();
        }
    }

    // JWT 토큰 복호화 -> 토큰 정보 확인
    public Authentication getAuthentication(String token) {

        Claims claims = parseClaims(token);

        // 권한 정보가 없으면 예외
        if (claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 Token 입니다.");
        }

        // 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 Principal, Crendential, Authorities와 함께 생성
        // 이후 Authentication 객체를 반환
        // 이때 유저는 Spring boot 자체 User class
        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }


    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        // 헤더에 토큰이 있는지 출력
        System.out.println("bearerToken = " + bearerToken);

        // 토큰이 유효하고, 'Bearer '로 시작하며 충분한 길이를 갖는지 확인
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE) && bearerToken.length() > 7) {
            return bearerToken.substring(7);  // 'Bearer ' 이후의 실제 토큰만 반환
        }

        return null;
    }
}
