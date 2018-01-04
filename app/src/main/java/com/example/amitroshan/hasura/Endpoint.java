package com.example.amitroshan.hasura;

/**
 * Created by amitroshan on 02/07/17.
 */

public class Endpoint {

    public static final String AUTH_URL = "https://auth.beadle13.hasura-app.io/";
    public static final String DB_URL = "https://data.beadle13.hasura-app.io/";
    public static final String VERSION = "v1";

    public static final String LOGIN = "login";
    public static final String REGISTER = "signup";
    public static final String LOGOUT = "user/logout";
    public static final String QUERY = Endpoint.VERSION + "/query";

}
