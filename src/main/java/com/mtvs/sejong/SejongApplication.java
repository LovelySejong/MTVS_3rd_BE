package com.mtvs.sejong;

import com.mtvs.sejong.user.domain.Authority;
import com.mtvs.sejong.user.domain.User;
import com.mtvs.sejong.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
public class SejongApplication {

    public static void main(String[] args) {
        SpringApplication.run(SejongApplication.class, args);
    }

    @Bean
    CommandLineRunner localServerStart(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepository.saveAll(Arrays.asList(
                    newUser("USER1", "user1@test.com", "test1234", passwordEncoder),
                    newUser("USER2", "user2@test.com", "test1234", passwordEncoder)
            ));
        };
    }

    private User newUser(String nickName, String email, String password, PasswordEncoder passwordEncoder) {
        return User.builder()
                .nickname(nickName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(Authority.USER)
                .build();
    }
}
