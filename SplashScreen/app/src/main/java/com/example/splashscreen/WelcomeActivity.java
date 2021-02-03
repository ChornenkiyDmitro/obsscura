package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    TextView sign_in, sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        sign_in = (TextView) findViewById(R.id.sign_in);
        sign_up = (TextView) findViewById(R.id.sign_up);
        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in:
                Intent singin = new Intent(WelcomeActivity.this, RegistrationActivity.class);
                singin.putExtra("frgToLoad", 1);
                startActivity(singin);
                break;
            case R.id.sign_up:
                Intent singup = new Intent(WelcomeActivity.this, RegistrationActivity.class);
                singup.putExtra("frgToLoad", 2);
                startActivity(singup);
                break;

        }
    }
}

