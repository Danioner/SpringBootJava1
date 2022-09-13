package com.example.demo.user;

import com.example.demo.filtering.SomeBean;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
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
    @GetMapping("/usersHateoas/{id}")
    public EntityModel<User> retrieveUserV2(@PathVariable Integer id){
        EntityModel<User> entityModel = EntityModel.of(userService.findOne(id));
        WebMvcLinkBuilder linkAllUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkAllUsers.withRel("all-users"));
        return entityModel;
    }
}
