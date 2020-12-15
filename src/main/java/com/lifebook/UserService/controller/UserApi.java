package com.lifebook.UserService.controller;

import com.lifebook.UserService.dto.UserDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping(path = "/user", produces = "application/json")
public interface UserApi {

    @PostMapping(path = "/{token}")
    UserDto login(@PathVariable("token") String token) throws IOException;

    @PostMapping(path = "/{userId}/subscribe/{key}")
    void Subscribe(@PathVariable("userId") String userId, @PathVariable("key") String key);

    @PostMapping(path = "/{userId}/unsubscribe/{key}")
    void unSubscribe(@PathVariable("userId") String userId, @PathVariable("key") String key);
}
