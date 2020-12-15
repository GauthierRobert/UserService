package com.lifebook.UserService.service;

import com.lifebook.UserService.domain.Subscription;
import com.lifebook.UserService.domain.User;
import com.lifebook.UserService.repository.SubscriptionRepository;
import com.lifebook.UserService.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final UserRepository userRepository;


    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }


    public void subscribe(String userId, String key) {
        User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new EntityNotFoundException("User not found with Id : " + userId));

        subscriptionRepository.save(new Subscription(key, user));
    }

    public void unSubscribe(String userId, String key) {
        subscriptionRepository.deleteByKeyAndUserId(key, UUID.fromString(userId));
    }


}
