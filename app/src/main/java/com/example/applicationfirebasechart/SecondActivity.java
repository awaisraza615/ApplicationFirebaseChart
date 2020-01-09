package com.example.applicationfirebasechart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String ages = getIntent().getStringExtra("Age");
        Toast.makeText(SecondActivity.this,ages, Toast.LENGTH_LONG).show();
        String ides = getIntent().getStringExtra("Id");
        Toast.makeText(SecondActivity.this,ides, Toast.LENGTH_LONG).show();
    }
}
