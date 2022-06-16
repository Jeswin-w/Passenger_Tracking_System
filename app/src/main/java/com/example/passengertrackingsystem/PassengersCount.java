package com.example.passengertrackingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PassengersCount extends AppCompatActivity {
    FirebaseDatabase database2=FirebaseDatabase.getInstance();
    DatabaseReference myRef2 = database2.getReference("totalvalue");
    Long reaminingseats;
    TextView passengertext,remaining;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passengers_count);
        passengertext=(TextView)findViewById(R.id.passengertextview);
        remaining=(TextView)findViewById(R.id.rem_seat);

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = (long) dataSnapshot.getValue();

                passengertext.setText((Long.toString(value)));
                Log.d("TAG", "Value is: " + value);
                if (52-value>=0)
                {
                    remaining.setText(Long.toString(52-value));
                }
                else
                {
                    remaining.setText("0");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(getApplicationContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void locationintent(View view) {
        Intent i = new Intent(getApplicationContext(), location.class);
        startActivity(i);
    }
}