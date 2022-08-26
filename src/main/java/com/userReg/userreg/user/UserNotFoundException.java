package com.userReg.userreg.user;

import java.security.MessageDigest;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}
