package com.lifebook.UserService.exception;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UUID userId) {
        super("User not found with Id : " + userId);
    }
}
