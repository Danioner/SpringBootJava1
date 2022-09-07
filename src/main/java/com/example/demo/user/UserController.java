package com.example.demo.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDao userService;
    public UserController(UserDao userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userService.findAll();
    }
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Integer id){
        return userService.findOne(id);
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User usersaved = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usersaved.getId()).toUri();
        System.out.println(usersaved.toString());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public List<User> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return userService.findAll();
    }
}
