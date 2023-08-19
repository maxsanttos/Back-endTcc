package com.max.gerenciadorTcc.controller;

import com.max.gerenciadorTcc.config.security.TokenService;
import com.max.gerenciadorTcc.controller.dto.UserInfoDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.max.gerenciadorTcc.domain.repository.UserRepository;
import com.max.gerenciadorTcc.domain.user.User;
import com.max.gerenciadorTcc.domain.user.dtos.AuthentaticationDTO;
import com.max.gerenciadorTcc.domain.user.dtos.LoginResponseDTO;
import com.max.gerenciadorTcc.domain.user.dtos.RegisterDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthentaticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());


        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (this.repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserInfoDTO>> getAllUsers() {
        List<User> users = repository.findAll();
        List<UserInfoDTO> userInfos = users.stream()
            .map(user -> new UserInfoDTO(user.getId(), user.getLogin(), user.getRole()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(userInfos);
    }
}
