package com.example.listviewproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView textView = findViewById(R.id.show);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", -1);
        String value= intent.getStringExtra("value");

        textView.setText("the index is : " + index + "\nthe value is : " + value);

    }
}
