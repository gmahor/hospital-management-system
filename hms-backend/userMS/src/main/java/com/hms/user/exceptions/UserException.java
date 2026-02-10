package com.hms.user.exceptions;

import java.text.MessageFormat;

public class UserException extends RuntimeException {

    public UserException(String username, String email) {
        super(MessageFormat.format(
                "User already found with the given username: {0} or email: {1}",
                username, email
        ));
    }

    public UserException(String email) {
        super(MessageFormat.format("User not found with the given email: {0}", email));
    }

    public UserException(Long id) {
        super(MessageFormat.format("User not found with the given id: {0}", id));
    }


}
