package com.example.splashscreen;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Fragment_signin extends Fragment  {
    DatabaseHelper db;
    View view;
    onFragmentChangeListener listener;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof onFragmentChangeListener)
            listener = (onFragmentChangeListener) getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_signin, null);

        db = new DatabaseHelper(getActivity());

        TextView tv_sign_in_sign_up = (TextView) view.findViewById(R.id.tv_sign_in_sign_up);
        tv_sign_in_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStackImmediate();
                }
                else
                if (listener != null) {
                    listener.onRout("sign up");
                }
            }
        });

        TextView forgot_your_password = (TextView) view.findViewById(R.id.forgot_your_password);
        forgot_your_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRout("forgot your password");
            }
        }});

Button button = (Button) view.findViewById(R.id.btn_sign_in);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onSignIn();
    }
});
        return view;
    }



    public void onSignIn() {
        EditText editTextEmail = (EditText) view.findViewById(R.id.et_sign_in_email);
        EditText editTextPassword = (EditText) view.findViewById(R.id.et_sign_in_password);
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        Boolean isUserExsists = db.checkUser(email, password);
        User user = new User();
        Log.d("IDdetails", String.valueOf(user.getId()));


        if (isUserExsists == true) {
            Intent HomePage = new Intent(getActivity(), InfoUserActivity.class);
            Bundle b = new Bundle();
            b.putString("textViewEmail", editTextEmail.getText().toString());
            b.putString("textViewPassword", editTextPassword.getText().toString());

            String y = db.selectOneUserSendUserName(email, password);
            int x = db.selectOneUserSendId(email,password);
            Log.d("TAG" , "ID =  " + x);

            b.putString("textViewUsername",y);
            b.putString("textViewId", String.valueOf(x));

            HomePage.putExtras(b);
            startActivity(HomePage);
        } else {
            Toast.makeText(getActivity(), "Login Error", Toast.LENGTH_LONG).show();
        }
    }
}









