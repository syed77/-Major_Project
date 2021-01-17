package com.communication.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AboutUs) this).getSupportActionBar().setTitle("About Us");
    }
}
