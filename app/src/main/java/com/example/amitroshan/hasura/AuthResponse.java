package com.example.amitroshan.hasura;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amitroshan on 02/07/17.
 */

public class AuthResponse {

    @SerializedName("auth_token")
    String authToken;

    @SerializedName("hasura_id")
    Integer id;

    @SerializedName("hasura_role")
    List<String> roles;

    public String getAuthToken(){
        return authToken;
    }

    public Integer getId(){
        return id;
    }

    public List<String> getRoles(){
        return roles;
    }

}
