package com.example.amitroshan.hasura;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Toolbar custom_action = (Toolbar) findViewById(R.id.ques_tool);
        setSupportActionBar(custom_action);

        getSupportActionBar().setTitle("");

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("Sample Questions");
    }
}
