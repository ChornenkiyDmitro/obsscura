package com.example.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;

import java.text.BreakIterator;

public class Fragment_password_recovery extends Fragment {

    NestedScrollView nestedScrollView;
    InputValidation inputValidation;
    DatabaseHelper db;
    View view;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_password_recovery, null);
        Button btn_get_a_password = (Button) view.findViewById(R.id.btn_get_a_password);
        db = new DatabaseHelper(getActivity());
        btn_get_a_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheck();


            }
        });
        return view;
    }

    public void onCheck() {
        EditText editTextEmail = (EditText) view.findViewById(R.id.et_rp_email);
        String email = editTextEmail.getText().toString().trim();
        if (editTextEmail.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Please fill your email", Toast.LENGTH_SHORT).show();
            return;
        }


        if (db.checkUser1(editTextEmail.getText().toString().trim())) {
            Fragment_password_recovery_create yfc = new Fragment_password_recovery_create();
            Bundle bundle = new Bundle();
            bundle.putString("tag", email);
            yfc.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_case,  yfc).commit();



        } else {
            Toast.makeText(getActivity(), getString(R.string.error_valid_email_password), Toast.LENGTH_LONG).show();
        }
    }
    }
