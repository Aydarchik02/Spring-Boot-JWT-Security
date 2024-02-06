package com.example.springbootjwtsecurityjava4.service;

import com.example.springbootjwtsecurityjava4.config.JwtUtils;
import com.example.springbootjwtsecurityjava4.model.User;
import com.example.springbootjwtsecurityjava4.model.enums.Role;
import com.example.springbootjwtsecurityjava4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public String register(String name, String email, String password) {
        if (userRepository.existsByEmail(email))
            throw new RuntimeException("email found");
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "register";
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("password invalid");
        return jwtUtils.generateToken(email);
    }
}
// User
// crud(save,findAll(admin))
// response,request,mapper(edit,view)