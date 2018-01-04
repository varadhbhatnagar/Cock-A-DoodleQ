package com.example.amitroshan.hasura;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class AlarmReceiver extends BroadcastReceiver
{
    public static final int REQUEST_CODE = 222;
    public static int INTENT_CODE = 0;
    private static Ringtone mRingtone  = null;

    @Override
    public void onReceive(final Context context, Intent intent)
    {
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        mRingtone  = RingtoneManager.getRingtone(context, alarmUri);
        mRingtone.play();

        if( INTENT_CODE == 0 ) {
            Intent i = new Intent();
            INTENT_CODE = 1;
            i.setClass(context, AlarmRinging.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("Id",Cache.task_id);
            context.startActivity(i);
        }

    }

    public static void stopRingtone() {
        mRingtone.stop();
    }

    public static void startpRingtone() {
        mRingtone.play();
    }
}

