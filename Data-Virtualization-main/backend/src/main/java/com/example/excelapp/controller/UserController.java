package com.example.excelapp.controller;
import com.example.excelapp.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;
    public UserController(UserService service){this.service=service;}
    @PostMapping("/register")
    public Map<String,String> register(@RequestParam String username,@RequestParam String password){
        return Map.of("status", service.register(username,password));
    }
    @PostMapping("/login")
    public Map<String,String> login(@RequestParam String username,@RequestParam String password){
        return Map.of("status", service.login(username,password));
    }
}
