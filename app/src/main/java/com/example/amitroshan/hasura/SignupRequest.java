package com.example.amitroshan.hasura;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amitroshan on 29/07/17.
 */

public class SignupRequest {

    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    @SerializedName("email")
    String email;

    public SignupRequest(String username, String password , String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
