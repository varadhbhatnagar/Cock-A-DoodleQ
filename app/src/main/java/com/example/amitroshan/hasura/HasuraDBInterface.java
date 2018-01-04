package com.example.amitroshan.hasura;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by amitroshan on 29/07/17.
 */

public interface HasuraDBInterface {

    @POST(Endpoint.QUERY)
    Call<List<AlarmRecord>> getAlarm(@Body ReadAlarm query);

    @POST(Endpoint.QUERY)
    Call<AlarmReturningResponse> addATodo(@Body AddAlarm query);

    @POST(Endpoint.QUERY)
    Call<AlarmReturningResponse> deleteAlarm(@Body DeleteAlarm query);
}
