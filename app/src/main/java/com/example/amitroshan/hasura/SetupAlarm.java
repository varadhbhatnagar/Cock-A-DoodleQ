package com.example.amitroshan.hasura;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetupAlarm extends AppCompatActivity {

    ListView alarm_list;
    private int mHour, mMinute;
    TimePickerDialog alarm_popup;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_alarm);

        Hasura.initialise(this);

        final Calendar c = Calendar.getInstance();
        final Calendar d = Calendar.getInstance();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarm_list = (ListView) findViewById(R.id.alarm_view);

        ArrayList<String> items = new ArrayList<>();

        final CustomAdapterr adap = new CustomAdapterr(this, items);

        alarm_list.setAdapter(adap);

        ((BaseAdapter) alarm_list.getAdapter()).notifyDataSetChanged();

        initialise_list(adap);

        FloatingActionButton floating_alarm = (FloatingActionButton) findViewById(R.id.floating_alarm);

        floating_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                 alarm_popup = new TimePickerDialog(SetupAlarm.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                                adap.add(i+":"+i1);

                                //New
                                d.set(Calendar.HOUR_OF_DAY, i);
                                d.set(Calendar.MINUTE, i1);
                                d.set(Calendar.SECOND, 0);

                                final int _id = ++Cache.task_id;

                                Intent intent = new Intent(SetupAlarm.this, AlarmReceiver.class);
                                pendingIntent = PendingIntent.getBroadcast(SetupAlarm.this, _id, intent, PendingIntent.FLAG_ONE_SHOT);
                                Cache.pendingIntent[_id] = pendingIntent;

                                time =(d.getTimeInMillis()-(d.getTimeInMillis()%60000));
                                if(System.currentTimeMillis()>time)
                                {
                                    if (d.AM_PM == 0)
                                        time = time + (1000*60*60*12);
                                    else
                                        time = time + (1000*60*60*24);
                                }
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time-5000, 10000, pendingIntent);
                                //Ends


                                Hasura.db.addATodo(new AddAlarm(Hasura.getUserId(),i+":"+i1)).enqueue(new Callback<AlarmReturningResponse>() {
                                    @Override
                                    public void onResponse(Call<AlarmReturningResponse> call, Response<AlarmReturningResponse> response) {
                                        if(response.isSuccessful()){
                                            //Toast.makeText(SetupAlarm.this, "Added Alarm", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(SetupAlarm.this, response.message(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<AlarmReturningResponse> call, Throwable t) {

                                    }
                                });
                            }
                        }, mMinute, mHour, false);

                alarm_popup.setTitle("Select Time");
                alarm_popup.show();



            }
        });
    }

    private void initialise_list( final CustomAdapterr adap ){

        final ProgressDialog progDailog = ProgressDialog.show(SetupAlarm.this,
                "Fetching Alarms",
                "....please wait....", true);

        final Calendar d = Calendar.getInstance();

        Hasura.db.getAlarm(new ReadAlarm(Hasura.getUserId())).enqueue(new Callback<List<AlarmRecord>>() {
            @Override
            public void onResponse(Call<List<AlarmRecord>> call, Response<List<AlarmRecord>> response) {
                if(response.isSuccessful()){
                    for (AlarmRecord i: response.body()) {
                        adap.add(i.getTime());
                        String temp[] = i.getTime().split("\\:");

                        int temp_hour, temp_min;
                        temp_hour = Integer.parseInt(temp[0]);
                        temp_min = Integer.parseInt(temp[1]);

                        //Toast.makeText(SetupAlarm.this,, Toast.LENGTH_SHORT).show();

                        //New
                        d.set(Calendar.HOUR_OF_DAY, temp_hour);
                        d.set(Calendar.MINUTE, temp_min);
                        d.set(Calendar.SECOND, 0);

                        final int _id = ++Cache.task_id;

                        Intent intent = new Intent(SetupAlarm.this, AlarmReceiver.class);
                        pendingIntent = PendingIntent.getBroadcast(SetupAlarm.this, _id , intent, PendingIntent.FLAG_ONE_SHOT);
                        Cache.pendingIntent[_id] = pendingIntent;

                        time =(d.getTimeInMillis()-(d.getTimeInMillis()%60000));
                        if(System.currentTimeMillis()>time)
                        {
                            if (d.AM_PM == 0)
                                time = time + (1000*60*60*12);
                            else
                                time = time + (1000*60*60*24);
                        }
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time-5000, 10000, pendingIntent);
                        //Ends

                    }
                }else{
                    Toast.makeText(SetupAlarm.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                }
                progDailog.dismiss();
            }

            @Override
            public void onFailure(Call<List<AlarmRecord>> call, Throwable t) {
                Toast.makeText(SetupAlarm.this, "Check your internet connection !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
