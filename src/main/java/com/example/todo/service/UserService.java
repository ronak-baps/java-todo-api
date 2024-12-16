package com.example.todo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;

@Service
@Configuration
public class UserService {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> registerUser(User user) {
        try {            
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            User dbUser = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(dbUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }

    public ResponseEntity<?> userLogin(User user) {
        Optional<User> dbUser = userRepository.findByEmailAndIsDeletedFalse(user.getEmail());
        if (passwordEncoder().matches(user.getPassword(), dbUser.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body(dbUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong Password");
        }
    }
}
