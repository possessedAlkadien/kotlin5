package com.petshop.PetShop_management.controller;

import com.petshop.PetShop_management.dto.LoginRequestDTO;
import com.petshop.PetShop_management.entity.User;
import com.petshop.PetShop_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

        if (user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Успешный вход в систему");
            response.put("userId", user.get().getUserId());
            response.put("email", user.get().getEmail());
            response.put("role", "USER");
            return ResponseEntity.ok(response);
        } 
        else {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }
    } 
    catch (Exception e) {
            return ResponseEntity.badRequest().body("Неверный email или пароль");
        }
    }

    @PostMapping("/logout")

    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Успешный выход из системы");
    }

}





