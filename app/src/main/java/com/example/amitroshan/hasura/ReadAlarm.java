package com.example.amitroshan.hasura;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amitroshan on 29/07/17.
 */

public class ReadAlarm {

    @SerializedName("type")
    String type = "select";

    @SerializedName("args")
    Args args;

    public ReadAlarm(Integer userId){
        args = new Args();
        args.where = new Where();
        args.where.userId = userId;
    }

    class Args{

        @SerializedName("table")
        String table = "alarm_table";

        @SerializedName("columns")
        String []columns = {"id","time"};

        @SerializedName("where")
        Where where;
    }

    class Where {
        @SerializedName("id")
        Integer userId;
    }
}
