package com.kruger.util;

public final class Constants {

    public static final String TYPES_RESPONSE = "application/json";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String OK = "OK";
    public static final String OK_CREATED = "CREATED";
    public static final String EMPLOYED_CREATED = "Employed Created";
    public static final String OK_DELETED = "DELETED";
    public static final Integer CODE_OK = 200;
    public static final Integer CODE_ERROR = 500;
    public static final Integer CODE_NOTFOUND = 404;
    public static final Integer CODE_CREATED = 201;
    public static final String SELECT_QUERY = "select * from";
    public static final String AUTHORITY_ADMIN = "hasAuthority('ADMIN')";
    public static final String AUTHORITY_EMPLOYEE = "hasAuthority('EMPLOYEE')";

    private Constants() {

    };
}