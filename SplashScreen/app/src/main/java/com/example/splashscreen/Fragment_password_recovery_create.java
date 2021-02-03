package com.example.splashscreen;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class Fragment_password_recovery_create extends Fragment {

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


db = new DatabaseHelper(getActivity());
        view = inflater.inflate(R.layout.fragment_password_recovery_create, null);
        Button SavePassword = (Button) view.findViewById(R.id.btn_save_pass);
        SavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });


        return view;
    }


    private  String getTrimValue(EditText view) {
        return view.getText().toString().trim();
    }

    public void changePassword() {

        Bundle bundle = getArguments();
        String email = bundle.getString("tag");


        String password = getTrimValue(view.findViewById(R.id.et_rp_pass));
        String passwordConf = getTrimValue(view.findViewById(R.id.et_rp_pass2));




        if (password.isEmpty() && passwordConf.isEmpty()) {
            Toast.makeText(getActivity(), "fill all fields ", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.contentEquals(passwordConf)) {
            Toast.makeText(getActivity(), "password doesn't match", Toast.LENGTH_LONG).show();
            return;
        }

        else {

            db.updatePassword(email, password);
            Toast.makeText(getActivity(), "password reset successfully", Toast.LENGTH_SHORT).show();
            if (listener != null) {
                listener.onRout("sign in");
            }
        }
    }
}



