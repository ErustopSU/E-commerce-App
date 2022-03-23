package com.hisu.hisumal;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {


    public static final String TEST_INTENT_KEY = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView txt = findViewById(R.id.txt_test);
        txt.setText(getIntent().getStringExtra(TEST_INTENT_KEY));
    }
}