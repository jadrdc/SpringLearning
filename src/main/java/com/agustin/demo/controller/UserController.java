package com.agustin.demo.controller;

import com.agustin.demo.models.User;
import com.agustin.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class UserController {
    private final UserService service;

    UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    List<User> all() {
        return service.findAll();
    }

    @PostMapping("/user")
    User newUser(@RequestBody User  user) {
        return service.save(user);
    }

    @GetMapping("/user/{id}")
    User one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/user/{id}")
    User replaceUser(@RequestBody User  user, @PathVariable Long id) {
        return service.updateUser(user, id);
    }
    @DeleteMapping("/user/{id}")
    void deleteUser(@PathVariable Long id) {
        service.deleteById(id);
    }


}
