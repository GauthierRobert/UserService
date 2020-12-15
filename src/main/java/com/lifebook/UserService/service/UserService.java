package com.lifebook.UserService.service;

import com.lifebook.UserService.domain.User;
import com.lifebook.UserService.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findBy(String facebookId) {
        return userRepository.findby(facebookId);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
