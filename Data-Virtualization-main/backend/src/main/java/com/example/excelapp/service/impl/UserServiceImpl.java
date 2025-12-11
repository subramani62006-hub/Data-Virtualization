package com.example.excelapp.service.impl;
import com.example.excelapp.model.User;
import com.example.excelapp.repository.UserRepository;
import com.example.excelapp.service.UserService;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    public UserServiceImpl(UserRepository repo){this.repo=repo;}

    @Override
    public String register(String username, String password){
        if(repo.findByUsername(username).isPresent()) return "username_taken";
        User u=new User(); u.setUsername(username); u.setPassword(password); repo.save(u); return "ok";
    }

    @Override
    public String login(String username, String password){
        return repo.findByUsername(username).filter(u->u.getPassword().equals(password)).map(u->"ok").orElse("fail");
    }
}
