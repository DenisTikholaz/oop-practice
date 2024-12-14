package com.denis2.practice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final String ADMIN_TOKEN = "your-secret-admin-token"; // Токен для доступу до панелі

    @PostMapping("/verify-token")
    public Map<String, Object> verifyToken(@RequestHeader("Authorization") String token) {
        Map<String, Object> response = new HashMap<>();
        if (token != null && token.equals("Bearer " + ADMIN_TOKEN)) {
            response.put("valid", true);
        } else {
            response.put("valid", false);
        }
        return response;
    }

    @PostMapping("/entity/create")
    public Map<String, Object> createEntity(@RequestBody Map<String, String> entity) {
        // Логіка для створення сутності
        Map<String, Object> response = new HashMap<>();
        response.put("name", entity.get("name"));
        return response;
    }

    @PutMapping("/entity/edit")
    public Map<String, Object> editEntity(@RequestBody Map<String, String> entity) {
        // Логіка для редагування сутності
        Map<String, Object> response = new HashMap<>();
        response.put("newName", entity.get("newName"));
        return response;
    }

    @DeleteMapping("/entity/delete")
    public Map<String, Object> deleteEntity(@RequestBody Map<String, String> entity) {
        // Логіка для видалення сутності
        Map<String, Object> response = new HashMap<>();
        response.put("id", entity.get("id"));
        return response;
    }
}
