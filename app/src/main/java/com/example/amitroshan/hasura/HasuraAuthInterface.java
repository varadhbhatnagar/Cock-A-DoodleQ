package com.example.amitroshan.hasura;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by amitroshan on 02/07/17.
 */

public interface HasuraAuthInterface {

    @POST(Endpoint.LOGIN)
    Call<AuthResponse> login(@Body AuthRequest request);

    @POST(Endpoint.REGISTER)
    Call<AuthResponse> register(@Body SignupRequest request);

    @GET(Endpoint.LOGOUT)
    Call<MessageResponse> logout();
}
