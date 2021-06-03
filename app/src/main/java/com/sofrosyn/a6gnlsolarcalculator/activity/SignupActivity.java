package com.sofrosyn.a6gnlsolarcalculator.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.sofrosyn.a6gnlsolarcalculator.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private MaterialButton btnLogin;
    private TextInputEditText registerDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();

     }

    private void initViews() {

     btnLogin = findViewById(R.id.fragment_register_btnLogin);
     registerDate = findViewById(R.id.register_date);


     btnLogin.setOnClickListener(this::onClick);
     registerDate.setOnClickListener(this::onClick);

    }

    private void startLoginFragment(){
        startActivity(new Intent(this,LoginActivity.class));
    }

    private void pickInstallDate(){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                SignupActivity.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
// If you're calling this from a support Fragment
//        dpd.show(getFragmentManager(), "Datepickerdialog");
// If you're calling this from an AppCompatActivity
 dpd.show(getSupportFragmentManager(), "Datepickerdialog");
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fragment_register_btnLogin:
                startLoginFragment();
                break;
            case R.id.register_date:
                pickInstallDate();
                break;

        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = ""+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        registerDate.setText(date);
    }
}