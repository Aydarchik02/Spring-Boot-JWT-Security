package com.example.springbootjwtsecurityjava4.api;

import com.example.springbootjwtsecurityjava4.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;

    @PostMapping("register")
    public String reg(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        return authService.register(name, email, password);
    }

    @PostMapping("login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return authService.login(email, password);
    }

    @GetMapping("admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String admin() {
        return "admin";
    }

    @GetMapping("user")
    @PreAuthorize("hasAnyAuthority('USER')")
    public String admins() {
        return "user";
    }
}