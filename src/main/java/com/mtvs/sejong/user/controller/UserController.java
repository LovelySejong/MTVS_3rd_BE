package com.mtvs.sejong.user.controller;

import com.mtvs.sejong._core.utils.ApiUtils;
import com.mtvs.sejong.user.dto.UserRequestDTO;
import com.mtvs.sejong.user.dto.UserResponseDTO;
import com.mtvs.sejong.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    /* 회원가입 */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserRequestDTO.signUpDTO requestDTO) {

        userService.signUp(requestDTO);

        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    /* 로그인 */
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest httpServletRequest, @Valid @RequestBody UserRequestDTO.loginDTO requestDTO) {

        UserResponseDTO.authTokenDTO responseDTO = userService.login(httpServletRequest, requestDTO);
        UserResponseDTO.UserDTO userDTO = userService.loginInfo(requestDTO.email());

        // 토큰과 사용자 정보를 포함한 응답 반환
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, responseDTO.grantType() + " " + responseDTO.accessToken())
                .body(ApiUtils.success(userDTO));
    }


    /* 로그아웃 */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {

        log.info("로그아웃 시도");

        userService.logout(httpServletRequest);

        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

    @GetMapping("test")
    public ResponseEntity<?> test(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(ApiUtils.success(null));
    }

}
