package com.app.bankrypt.controller;

import com.app.bankrypt.dto.UserCreationDTO;
import com.app.bankrypt.model.Users;
import com.app.bankrypt.repository.UserRepository;
import com.app.bankrypt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepo;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody UserCreationDTO dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<Users>> searchAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Users> searchUser(@PathVariable Long id){
        return userService.findUserById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
