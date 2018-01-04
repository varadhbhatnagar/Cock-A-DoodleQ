package com.example.amitroshan.hasura;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amitroshan on 29/07/17.
 */

public class AlarmReturningResponse {

    @SerializedName("affected_rows")
    Integer affectedRows;

    @SerializedName("returning")
    List<AlarmRecord> todoRecords;

    public Integer getAffectedRows() {
        return affectedRows;
    }

    public List<AlarmRecord> getAlarmRecords() {
        return todoRecords;
    }
}
