package com.faisal.experiment.planegames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private PlaneAnimation pa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pa= new PlaneAnimation(this);
        setContentView(pa);
    }
}
