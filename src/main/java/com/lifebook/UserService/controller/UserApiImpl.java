package com.lifebook.UserService.controller;

import com.lifebook.UserService.domain.FacebookProfile;
import com.lifebook.UserService.domain.Subscription;
import com.lifebook.UserService.domain.User;
import com.lifebook.UserService.dto.UserDto;
import com.lifebook.UserService.service.FacebookService;
import com.lifebook.UserService.service.SubscriptionService;
import com.lifebook.UserService.service.UserService;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.lifebook.UserService.domain.builder.UserBuilder.anUser;
import static com.lifebook.UserService.domain.builder.UserBuilder.modify;

@RestController
public class UserApiImpl implements UserApi {

    private final FacebookService facebookService;
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    @Qualifier("userExchange")
    private final TopicExchange userExchange;
    private final RabbitTemplate rabbitTemplate;


    public UserApiImpl(FacebookService facebookService, UserService userService, SubscriptionService subscriptionService, TopicExchange userExchange, RabbitTemplate rabbitTemplate) {
        this.facebookService = facebookService;
        this.userService = userService;
        this.subscriptionService = subscriptionService;
        this.userExchange = userExchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public UserDto login(String token) throws IOException {

        FacebookProfile facebookProfile = facebookService.getFacebookProfile(token);

        User user = userService.findBy(facebookProfile.getId());

        if (user == null) {
            user = userService.save(anUser().withProfile(facebookProfile).build());
        } else if (!user.getProfile().equals(facebookProfile)) {
            user = userService.save(modify(user).withProfile(facebookProfile).build());
            rabbitTemplate.convertAndSend(userExchange.getName(), "user.updated", convert(user));
        }

        return convert(user);
    }

    private UserDto convert(User user) {
        return new UserDto(user.getId(),
                user.getProfile().getName(),
                user.getProfile().getImageUrl(),
                user.getSubscriptions().stream().map(Subscription::getKey).collect(Collectors.toList()));
    }

    @Override
    public void Subscribe(String userId, String key) {
        subscriptionService.subscribe(userId, key);
    }

    @Override
    public void unSubscribe(String userId, String key) {
        subscriptionService.unSubscribe(userId, key);
    }
}
