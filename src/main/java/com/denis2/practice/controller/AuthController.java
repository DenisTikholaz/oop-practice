package com.denis2.practice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    private final SecureRandom secureRandom = new SecureRandom();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Перевірка логіну та паролю
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            long expirationTime = System.currentTimeMillis() + 5 * 60 * 1000; // Термін дії токену - 5 хвилин
            String randomTokenPart = generateRandomTokenPart();
            String token = Base64.getEncoder().encodeToString((username + ":" + expirationTime + ":" + randomTokenPart).getBytes());
            return ResponseEntity.ok(Map.of("token", token)); // Повертаємо токен у відповіді
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Невірний логін або пароль");
    }

    private String generateRandomTokenPart() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}

