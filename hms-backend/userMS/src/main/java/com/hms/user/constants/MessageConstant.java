package com.hms.user.constants;


public class MessageConstant {



    private MessageConstant() {
        throw new IllegalArgumentException("MessageConstant Is a utility class");
    }

    public static final String USER_REGISTER_SUCCESS = "User Register Successfully";
    public static final String ERROR_WHILE_REGISTER_USER = "Error While Register User";
    public static final String USER_LOGIN_SUCCESS = "User Login Successfully";
    public static final String ERROR_WHILE_LOGIN = "Error While Login";
    public static final String USER_FETCHED_SUCCESS = "User Fetched Successfully";
    public static final String USER_NOT_FOUND = "User Not Found";
    public static final String ERROR_WHILE_GETTING_USER = "Error While Get User";
    public static final String TOKEN_EXPIRED = "Token Is Expired";
    public static final String TOKEN_NOT_VALID = "Token Is Not Valid";
    public static final String NEW_TOKEN_GENERATED = "New Token Generated Successfully";
    public static final String ERROR_WHILE_GENERATING_TOKEN = "Error While Generating Token New Token";
    public static final String INVALID_CREDENTIAL = "Invalid Credential Check Email And Password";


}