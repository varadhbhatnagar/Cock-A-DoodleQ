package com.example.amitroshan.hasura;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amitroshan on 29/07/17.
 */

public class AddAlarm {

    @SerializedName("type")
    String type = "insert";

    @SerializedName("args")
    Args args;

    public AddAlarm(Integer userId, String time) {
        args = new Args();
        args.objects = new ArrayList<>();
        AlarmRecord record = new AlarmRecord(userId, time);
        args.objects.add(record);
    }

    class Args {

        @SerializedName("table")
        String table = "alarm_table";

        @SerializedName("returning")
        String[] returning = {
                "id","time"
        };

        @SerializedName("objects")
        List<AlarmRecord> objects;

    }

}
