package com.home.work.service.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {

    private static final String MESSAGE = "User not found!";

    public UserNotFoundException() {
        super(UserNotFoundException.MESSAGE);
    }

}
