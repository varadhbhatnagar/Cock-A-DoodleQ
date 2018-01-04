package com.example.amitroshan.hasura;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AlarmRinging extends AppCompatActivity {

    Button alarm_cancel;
    String ans;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_ringing);

        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        final EditText answer_input = (EditText) findViewById(R.id.answer_input);
        TextView question_input = (TextView) findViewById(R.id.question_input);

        Question_List.returnQuestions();

        question_input.setText( Question_List.randomKey);
        answer_input.setHint(Question_List.randomValue+" ?");

        AlarmReceiver.INTENT_CODE = 1;

        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for( int i = 0 ; i < 10 ; i++ ){
                    try {
                        Thread.sleep(33000);
                        AlarmReceiver.startpRingtone();
                    }catch(Exception e){
                        AlarmReceiver.stopRingtone();
                    }
                }
            }
        });


        alarm_cancel = (Button) findViewById(R.id.alarm_cancel);
        alarm_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ans = answer_input.getText().toString();

                if( ans.equalsIgnoreCase(Question_List.randomValue) ){
                    AlarmReceiver.stopRingtone();
                    //int temp_id = Integer.parseInt(getIntent().getStringExtra("Id"));
                    t.stop();
                    //Toast.makeText(getApplicationContext(),temp_id,Toast.LENGTH_SHORT).show();
                    //Cache.pendingIntent[temp_id].cancel();
                    //alarmManager.cancel(Cache.pendingIntent[temp_id]);
                    AlarmReceiver.INTENT_CODE = 0;
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please check your answer !!",Toast.LENGTH_SHORT).show();
                }

            }
        });

       t.start();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Answer to Exit...", Toast.LENGTH_SHORT).show();
    }

}
