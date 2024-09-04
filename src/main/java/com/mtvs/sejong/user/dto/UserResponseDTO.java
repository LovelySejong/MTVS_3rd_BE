package com.mtvs.sejong.user.dto;

public class UserResponseDTO {

    // 토큰 발급
    public record authTokenDTO(
            String grantType,
            String accessToken,
            Long accessTokenValidTime
    ) {
    }

}
