package com.agustin.demo.service;

import com.agustin.demo.exceptions.ProductNotFoundException;
import com.agustin.demo.exceptions.UserNotFoundException;
import com.agustin.demo.models.Product;
import com.agustin.demo.models.User;
import com.agustin.demo.repositories.ProductRepository;
import com.agustin.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional
    public User  updateUser(User user, Long id) {
        User updateUser = repository.findById(id).map(user1 -> {
                    user1.setName(user.getName());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    User user1 = new User();
                    user.setName(user.getName());
                    return repository.save(user);
                });
        return updateUser;
    }

    public List<User> findAll() {
        return repository.findAll();
    }


    public User save(User user) {
        return repository.save(user);
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
