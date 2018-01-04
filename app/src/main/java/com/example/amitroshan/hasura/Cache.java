package com.example.amitroshan.hasura;

import android.app.PendingIntent;

/**
 * Created by amitroshan on 30/07/17.
 */

public class Cache {

    static  PendingIntent pendingIntent[] = new PendingIntent[100];
    static int task_id = 0;

    public static void SettingIntent( PendingIntent p){
        //pendingIntent = p;
        pendingIntent[task_id] = p;
    }
}
