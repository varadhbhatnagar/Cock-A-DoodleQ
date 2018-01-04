package com.example.amitroshan.hasura;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amitroshan on 02/07/17.
 */

public class MessageResponse {

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }
}
