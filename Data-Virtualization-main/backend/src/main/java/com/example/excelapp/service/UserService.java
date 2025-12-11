package com.example.excelapp.service;
public interface UserService {
    String register(String username, String password);
    String login(String username, String password);
}
