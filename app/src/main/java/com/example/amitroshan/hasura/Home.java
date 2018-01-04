package com.example.amitroshan.hasura;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends AppCompatActivity implements View.OnClickListener{

    Button set_bt, question_bt, help_bt, logout_bt;

    public static void startActivity(Activity startingActivity) {
        startingActivity.startActivity(new Intent(startingActivity, Login.class));
        startingActivity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Hasura.initialise(this);

        //referencing the buttons
        set_bt = (Button) findViewById(R.id.set_bt);
        question_bt = (Button) findViewById(R.id.question_bt);
        help_bt = (Button) findViewById(R.id.help_bt);
        logout_bt = (Button) findViewById(R.id.logout_bt);

        logout_bt.setOnClickListener(this);
        set_bt.setOnClickListener(this);
        question_bt.setOnClickListener(this);
        help_bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.set_bt:
                startActivity(new Intent(getApplication(),SetupAlarm.class));

                break;
            case R.id.question_bt:
                startActivity(new Intent(getApplicationContext(),Questions.class));

                break;
            case R.id.help_bt:
                help_method();

                break;
            case R.id.logout_bt:
                //logout_method();
                final ProgressDialog progDailog = ProgressDialog.show(Home.this,
                        "Logging Out",
                        "....please wait....", true);
                Hasura.auth.logout().enqueue(new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                        //logout_method();
                        progDailog.dismiss();
                        if(response.isSuccessful()){
                            logout_method();
                        }
                        else {
                            Toast.makeText(Home.this, "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        Toast.makeText(Home.this, "Check your internet connection!! !!", Toast.LENGTH_SHORT).show();

                    }
                });
                break;
        }
    }

    private void help_method() {

        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(getBaseContext());
        View promptsView = li.inflate(R.layout.help, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Home.this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text

                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void logout_method() {

        Hasura.clearSession();
        Home.startActivity(Home.this);
    }
}
