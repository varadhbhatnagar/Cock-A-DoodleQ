package com.example.amitroshan.hasura;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup extends AppCompatActivity {

    EditText signup_name, signup_email, signup_password, signup_reenter;
    Button create_account;

    public static void startActivity(Activity startingActivity) {
        startingActivity.startActivity(new Intent(startingActivity, Home.class));
        startingActivity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Hasura.initialise(this);

        signup_email = (EditText) findViewById(R.id.signup_email);
        signup_name = (EditText) findViewById(R.id.signup_name);
        signup_password = (EditText) findViewById(R.id.signup_password);
        signup_reenter = (EditText) findViewById(R.id.signup_re_enter);
        create_account = (Button) findViewById(R.id.signup_create);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_method();
            }
        });

        if (Hasura.getUserSessionId() != null) {
            Signup.startActivity(this);
        }
    }

    private void register_method(){

        String username = signup_name.getText().toString().trim();
        String password = signup_password.getText().toString().trim();
        String reenter = signup_reenter.getText().toString().trim();
        String email = signup_email.getText().toString().trim();

        final ProgressDialog progDailog = ProgressDialog.show(Signup.this,
                "Signing You Up",
                "....please wait....", true);

        if( password.equals(reenter)){

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Endpoint.AUTH_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            HasuraAuthInterface auth = retrofit.create(HasuraAuthInterface.class);
            auth.register(new SignupRequest(username, password, email)).enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    if (response.isSuccessful()) {
                        //AuthResponse authResponse = response.body();
                        //Handle response
                        //Toast.makeText(getApplicationContext(),"User with ID: "+authResponse.getId()+" is successfully created",Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getApplicationContext(), Home.class));

                        Hasura.setSession(response.body());
                        //AuthResponse authResponse = response.body();

                        Signup.startActivity(Signup.this);

                    } else {
                        //Handle Error
                        Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();

                    }
                    progDailog.dismiss();
                }

                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                    //Handle Failure
                    progDailog.dismiss();
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),"Password Match Error !!",Toast.LENGTH_SHORT).show();
        }
    }
}
