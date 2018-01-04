package com.example.amitroshan.hasura;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amitroshan on 02/07/17.
 */

public class AuthRequest {

    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    public AuthRequest(String username, String password ){
        this.username = username;
        this.password = password;
    }
}
