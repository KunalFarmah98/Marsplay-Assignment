package com.apps.kunalfarmah.marsplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {

        private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        tv = findViewById(R.id.fulltext);
        tv.setText(getIntent().getStringExtra("Text"));

    }
}
