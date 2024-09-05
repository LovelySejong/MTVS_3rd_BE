package com.mtvs.sejong.user.service;

import com.mtvs.sejong._core.error.exception.Exception400;
import com.mtvs.sejong._core.error.exception.Exception401;
import com.mtvs.sejong._core.error.exception.Exception404;
import com.mtvs.sejong._core.jwt.JWTTokenProvider;
import com.mtvs.sejong.user.domain.Authority;
import com.mtvs.sejong.user.domain.User;
import com.mtvs.sejong.user.dto.UserRequestDTO;
import com.mtvs.sejong.user.dto.UserResponseDTO;
import com.mtvs.sejong.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JWTTokenProvider jwtTokenProvider;

    /* 기본 회원 가입 */
    @Transactional
    public void signUp(UserRequestDTO.signUpDTO requestDTO) {

        // 비밀번호 확인
        checkValidPassword(requestDTO.password(), passwordEncoder.encode(requestDTO.confirmPassword()));

        // 회원 생성
        User user = newUser(requestDTO);

        // 회원 저장
        userRepository.save(user);
    }

    // 로그인 토큰
    public UserResponseDTO.authTokenDTO login(HttpServletRequest httpServletRequest, UserRequestDTO.loginDTO requestDTO) {

        // 1. 이메일 확인
        User user = findMemberByEmail(requestDTO.email())
                .orElseThrow(() -> new Exception401("해당 이메일은 회원 가입 되지 않은 이메일입니다."));

        // 2. 비밀번호 확인
        checkValidPassword(requestDTO.password(), user.getPassword());

        return getAuthTokenDTO(requestDTO.email(), requestDTO.password(), httpServletRequest);
    }

    // 로그인 유저 정보
    public UserResponseDTO.UserDTO loginInfo(String email) {
        User user = findMemberByEmail(email)
                .orElseThrow(() -> new Exception401("사용자를 찾을 수 없습니다."));

         return new UserResponseDTO.UserDTO(user.getId(), user.getNickname());
    }


    // 비밀번호 확인
    private void checkValidPassword(String rawPassword, String encodedPassword) {

        log.info("{} {}", rawPassword, encodedPassword);

        if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new Exception400("비밀번호가 일치하지 않습니다.");
        }
    }

    protected Optional<User> findMemberByEmail(String email) {
        log.info("회원 확인 : {}", email);

        return userRepository.findByEmail(email);
    }

    // 회원 생성
    protected User newUser(UserRequestDTO.signUpDTO requestDTO) {
        return User.builder()
                .email(requestDTO.email())
                .password(passwordEncoder.encode(requestDTO.password()))
                .nickname(requestDTO.nickName())
                .authority(Authority.USER)
                .build();
    }

    // 토큰 발급
    protected UserResponseDTO.authTokenDTO getAuthTokenDTO(String email, String password, HttpServletRequest httpServletRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(email, password);
        AuthenticationManager manager = authenticationManagerBuilder.getObject();
        Authentication authentication = manager.authenticate(usernamePasswordAuthenticationToken);

        UserResponseDTO.authTokenDTO authTokenDTO = jwtTokenProvider.generateToken(authentication);

        return authTokenDTO;
    }


    /* 로그아웃 */
    public void logout(HttpServletRequest httpServletRequest) {

        log.info("로그아웃 - Refresh Token 확인");

        String token = jwtTokenProvider.resolveToken(httpServletRequest);

        if(token == null || !jwtTokenProvider.validateToken(token)) {
            throw new Exception400("유효하지 않은 Refresh Token 입니다.");
        }

    }
}
