package com.springboot.uber.controllers;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.springboot.uber.TestContainerConfiguration;
import com.springboot.uber.dto.OnBoardDriverDto;
import com.springboot.uber.dto.SignupDto;
import com.springboot.uber.entities.User;
import com.springboot.uber.entities.enums.Role;
import com.springboot.uber.repositories.RiderRepository;
import com.springboot.uber.repositories.UserRepository;

@AutoConfigureWebTestClient(timeout = "10000")
@Import(TestContainerConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RiderRepository riderRepository;

    private User user;

    @BeforeEach
    void setUpEach() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRoles(Set.of(Role.RIDER));
    }

    @Test
    void testSignUp_success() {
        SignupDto signupDto = new SignupDto();
        signupDto.setEmail("test@example.com");
        signupDto.setName("Test name");
        signupDto.setPassword("password");

        webTestClient.post()
                .uri("/auth/signup")
                .bodyValue(signupDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.data.email").isEqualTo(signupDto.getEmail())
                .jsonPath("$.data.name").isEqualTo(signupDto.getName());
    }

    // @Test
    // @WithUserDetails("admin@gmail.com")
    void testOnboardDriver_success() {

        if (!userRepository.existsById(1L)) {
            userRepository.save(user);
        }

        OnBoardDriverDto onboardDriverDto = new OnBoardDriverDto();
        onboardDriverDto.setVehicleId("ABC123");

        webTestClient
                .post()
                .uri("/auth/onBoardNewDriver/1")
                .bodyValue(onboardDriverDto)
                .exchange()
                .expectStatus().isCreated();
    }
}
