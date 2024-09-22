package com.mtvs.sejong.user.dto;

public class UserResponseDTO {

    // 토큰 발급
    public record authTokenDTO(
            String grantType,
            String accessToken,
            Long accessTokenValidTime
    ) {
    }

    // 로그인 성공 시 반환하는 DTO
    public record LoginSuccessDTO(
            String grantType,
            String accessToken,
            Long accessTokenValidTime,
            Long id,
            String nickname
    ) {
    }

    // getUSerProfile
    public record UserProfileDTO(
            String nickname
    ) {
    }
}
