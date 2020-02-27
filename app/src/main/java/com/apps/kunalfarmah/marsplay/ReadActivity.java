package com.apps.kunalfarmah.marsplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {

        private TextView tv;
        private LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        tv = findViewById(R.id.fulltext);
        tv.setText(getIntent().getStringExtra("Text"));
        ll = findViewById(R.id.content_read);

        Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        a.reset();
        ll.clearAnimation();
        ll.startAnimation(a);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        a.reset();
        ll.clearAnimation();
        ll.startAnimation(a);
    }
}
