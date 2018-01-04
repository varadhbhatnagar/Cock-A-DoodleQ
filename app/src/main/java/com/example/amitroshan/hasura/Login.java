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

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText login_username, login_password;
    Button login_button, signup_button;

    public static void startActivity(Activity startingActivity) {
        startingActivity.startActivity(new Intent(startingActivity, Home.class));
        startingActivity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Hasura.initialise(this);

        login_username = (EditText) findViewById(R.id.login_username);
        login_password = (EditText) findViewById(R.id.login_password);
        login_button = (Button) findViewById(R.id.login_button);
        signup_button = (Button) findViewById(R.id.signup_button);

        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);

        if (Hasura.getUserSessionId() != null) {
            Login.startActivity(this);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                login_method();

                break;
            case R.id.signup_button:
                startActivity(new Intent(getApplicationContext(),Signup.class));

                break;
        }
    }

    private void login_method() {

        String username = login_username.getText().toString();
        String password = login_password.getText().toString();

        final ProgressDialog progDailog = ProgressDialog.show(Login.this,
                "Authenticating",
                "....please wait....", true);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Endpoint.AUTH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HasuraAuthInterface auth = retrofit.create(HasuraAuthInterface.class);

        auth.login(new AuthRequest(username, password)).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    Hasura.setSession(response.body());
                    //AuthResponse authResponse = response.body();

                    Login.startActivity(Login.this);


                    //Handle Response
                    //Toast.makeText(getApplicationContext(),"User with ID: "+authResponse.getId()+" is successfully Logged In",Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(getApplicationContext(), Home.class));
                } else {
                    //Handle Error

                }
                progDailog.dismiss();
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                //Handle Failure
                Toast.makeText(getApplicationContext(),"Something Went Wrong !!",Toast.LENGTH_SHORT).show();
                progDailog.dismiss();
            }
        });

    }
}
