package com.example.amitroshan.hasura;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amitroshan on 29/07/17.
 */

public class AlarmRecord {

    @SerializedName("id")
    Integer id;

    @SerializedName("time")
    String time;

    public AlarmRecord(Integer id, String time){
        this.id = id;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
