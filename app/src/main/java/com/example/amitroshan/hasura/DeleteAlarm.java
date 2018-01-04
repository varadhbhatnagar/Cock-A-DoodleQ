package com.example.amitroshan.hasura;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amitroshan on 30/07/17.
 */

public class DeleteAlarm {


    @SerializedName("type")
    String type = "delete";

    @SerializedName("args")
    Args args;

    public DeleteAlarm(Integer id, String time) {
        args = new Args();
        args.where = new Where();
        args.where.id = id;
        args.where.time = time;
    }

    class Args {

        @SerializedName("table")
        String table = "alarm_table";

        @SerializedName("where")
        Where where;
    }

    class Where {
        @SerializedName("time")
        String time;

        @SerializedName("id")
        Integer id;
    }
}
