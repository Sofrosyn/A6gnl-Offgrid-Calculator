package com.sofrosyn.a6gnlsolarcalculator.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sofrosyn.a6gnlsolarcalculator.R;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton btnLogin;
    private MaterialButton register;
    private TextInputEditText emailTextLogin;
    private TextInputLayout emailLayoutLogin;
    private TextInputEditText passwordTextLogin;
    private TextInputLayout passwordLayoutLogin;
    private FirebaseAuth firebaseAuth;
    private LinearProgressIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();


    }

    private void startRegisterFragment() {
        startActivity(new Intent(this,SignupActivity.class));
    }

        private void initView() {
        btnLogin = findViewById(R.id.login_button);
        register = findViewById(R.id.fragment_login_btnSignup);
        emailTextLogin = findViewById(R.id.login_edit_text_email);
        emailLayoutLogin = findViewById(R.id.login_edit_layout_email);
        passwordTextLogin = findViewById(R.id.login_edit_text_password);
        passwordLayoutLogin = findViewById(R.id.login_edit_layout_password);
        indicator = findViewById(R.id.loginIndicator);
        firebaseAuth = FirebaseAuth.getInstance();

        //set click listener

        btnLogin.setOnClickListener(this::onClick);
        register.setOnClickListener(this::onClick);
    }


   private boolean validateUser(){
        if(TextUtils.isEmpty(emailTextLogin.getText()) || TextUtils.isEmpty(passwordTextLogin.getText())){
            Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),emailTextLogin.getText())){
            emailLayoutLogin.setError("invalid email");
            return false;
        }

            return true;
   }








private void loginUser(){
      if(!validateUser()){
          return;
      }  else {
/*          indicator.setVisibility(View.VISIBLE);
          firebaseAuth.signInWithEmailAndPassword(emailTextLogin.getText().toString(),passwordTextLogin.getText().toString())
                  .addOnCompleteListener(this,task -> {

                 if(task.isSuccessful()){
                     //Todo
                     indicator.setVisibility(View.INVISIBLE);
                 }     else {
                     indicator.setVisibility(View.INVISIBLE);
                     //Todo
                 }
          });*/
     startActivity(new Intent(this,MainActivity.class));
      }

}


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_login_btnSignup:
                startRegisterFragment();
                break;
            case R.id.login_button:
                loginUser();
                break;
        }
    }
}