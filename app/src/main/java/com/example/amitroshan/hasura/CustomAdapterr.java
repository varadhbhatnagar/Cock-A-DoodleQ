package com.example.amitroshan.hasura;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amitroshan on 29/07/17.
 */

public class CustomAdapterr extends ArrayAdapter<String> {

    TextView alarm_text;
    Button remove_alarm;

    public CustomAdapterr(Context context, ArrayList<String> items){
        super(context, 0, items);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final String time = getItem(position);

        if( convertView == null ){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

        alarm_text = (TextView) convertView.findViewById(R.id.alarm_text);
        remove_alarm = (Button) convertView.findViewById(R.id.remove_alarm);

        remove_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Hasura.db.deleteAlarm( new DeleteAlarm(Hasura.getUserId(),time)).enqueue(new Callback<AlarmReturningResponse>() {
                    @Override
                    public void onResponse(Call<AlarmReturningResponse> call, Response<AlarmReturningResponse> response) {
                        if(response.isSuccessful()){
                            remove(time);
                            //Toast.makeText(view.getContext(),"Alarm deleted",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(view.getContext(),"Something went wrong !!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AlarmReturningResponse> call, Throwable t) {
                        Toast.makeText(view.getContext(),"Check your internet connection !!",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        alarm_text.setText(time);

        return convertView;


    }
}
