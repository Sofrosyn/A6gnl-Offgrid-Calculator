package com.sofrosyn.a6gnlsolarcalculator.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.sofrosyn.a6gnlsolarcalculator.R
import com.sofrosyn.a6gnlsolarcalculator.Utils.Extensions.error
import com.sofrosyn.a6gnlsolarcalculator.Utils.Extensions.success
import com.sofrosyn.a6gnlsolarcalculator.Utils.Extensions.toast
import com.sofrosyn.a6gnlsolarcalculator.Utils.FirebaseUtils.firebaseAuth
import com.sofrosyn.a6gnlsolarcalculator.Utils.FirebaseUtils.firebaseUser
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var btnLogin: MaterialButton? = null
    private var register: MaterialButton? = null
    private var emailTextLogin: TextInputEditText? = null
    private var emailLayoutLogin: TextInputLayout? = null
    private var passwordTextLogin: TextInputEditText? = null
    private var passwordLayoutLogin: TextInputLayout? = null
    private var indicator: LinearProgressIndicator? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun startRegisterFragment() {
        startActivity(Intent(this, SignupActivity::class.java))
        finish()
        }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
     }


    private fun initView() {
        btnLogin = findViewById(R.id.login_button)
        register = findViewById(R.id.fragment_login_btnSignup)
        emailTextLogin = findViewById(R.id.login_edit_text_email)
        emailLayoutLogin = findViewById(R.id.login_edit_layout_email)
        passwordTextLogin = findViewById(R.id.login_edit_text_password)
        passwordLayoutLogin = findViewById(R.id.login_edit_layout_password)
        indicator = findViewById(R.id.loginIndicator)



        //set click listener
        btnLogin!!.setOnClickListener(this)
       register!!.setOnClickListener(this)
    }

    private fun validateUser(): Boolean {
        if (TextUtils.isEmpty(emailTextLogin?.text) || TextUtils.isEmpty(passwordTextLogin?.text)) {
            //Toast.makeText(this, getString(R.string.all_fields_required), Toast.LENGTH_SHORT).show()
            emailTextLogin?.error = getString(R.string.all_fields_required)
            passwordTextLogin?.error = getString(R.string.all_fields_required)
            return false
        }
        if (!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(), emailTextLogin!!.text)) {
            emailLayoutLogin!!.error = getString(R.string.invalid_email)
            return false
        }
        return true
    }

    private fun loginUser() {
        if (!validateUser()) {
            return
        } else {
          initFirebaseLogin()
        }
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.fragment_login_btnSignup -> startRegisterFragment()
            R.id.login_button -> loginUser()
        }
    }



    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, MainActivity::class.java))
            toast("welcome back")
        }
    }


    private fun initFirebaseLogin() {
        indicator?.visibility = View.VISIBLE
        firebaseAuth.signInWithEmailAndPassword(emailTextLogin?.text?.trim().toString(),
            passwordTextLogin?.text?.trim().toString()).addOnCompleteListener { task ->

            if (task.isSuccessful){
                indicator?.visibility = View.INVISIBLE
                success(getString(R.string.account_created))
                sendEmailVerification(emailTextLogin?.text?.trim().toString())
                startMainActivity()


            }else {
               error(getString(R.string.invalid_details))
            }
        }

    }



    private fun sendEmailVerification(user:String) {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("email sent to $user")
                }
            }
        }
    }




}