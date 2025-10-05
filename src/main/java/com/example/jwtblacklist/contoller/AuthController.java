package com.example.jwtblacklist.controller;

import com.example.jwtblacklist.model.AuthRequest;
import com.example.jwtblacklist.model.AuthResponse;
import com.example.jwtblacklist.service.JwtService;
import com.example.jwtblacklist.service.BlacklistService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final BlacklistService blacklistService;

    public AuthController(JwtService jwtService, BlacklistService blacklistService) {
        this.jwtService = jwtService;
        this.blacklistService = blacklistService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        String token = jwtService.generateToken(request.getUsername());
        return new AuthResponse(token);
    }

    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        blacklistService.blacklistToken(token);
        return "Logged out successfully.";
    }
}
