package com.example.splashscreen;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class RegistrationActivity extends AppCompatActivity implements onFragmentChangeListener {


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);
            getSupportActionBar().hide();
            int intentFragment = getIntent().getExtras().getInt("frgToLoad");
            switch (intentFragment){
                case 1:
                    onSignIn();
                    break;
                case 2:
                    onSignUp();
                    break;
        }
}


    @Override
    public void onRout(String type) {
        switch (type) {
            case "sign in" :
                   onSignIn();
                    break;
            case "sign up" :
                   onSignUp();
                    break;
            case "forgot your password":
                    FragmentManager fm5 = getSupportFragmentManager();
                    FragmentTransaction ft5 = fm5.beginTransaction();
                    ft5.replace(R.id.fragment_case, new Fragment_password_recovery());
                    ft5.addToBackStack(null);
                    ft5.commit();
                    break;
        }
    }

    public void onSignIn(){
        FragmentManager fm1 = getSupportFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        ft1.replace(R.id.fragment_case,new Fragment_signin());
        ft1.commit();
    }
    public void onSignUp(){
        FragmentManager fm2 = getSupportFragmentManager();
        FragmentTransaction ft2 = fm2.beginTransaction();
        ft2.replace(R.id.fragment_case,new Fragment_signup());
        ft2.commit();
    }



}

