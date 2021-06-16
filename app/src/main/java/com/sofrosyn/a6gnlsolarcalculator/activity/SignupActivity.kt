package com.sofrosyn.a6gnlsolarcalculator.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.sofrosyn.a6gnlsolarcalculator.R
import com.sofrosyn.a6gnlsolarcalculator.Utils.Extensions.textEdit2String
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.*

class SignupActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private var btnLogin: MaterialButton? = null
    private var registerDate: TextInputEditText? = null
    private var registerEmail: TextInputEditText? = null
    private var registerPassword: TextInputEditText? = null
    private var registerPhoneNumber: TextInputEditText? = null
    private var indicator : LinearProgressIndicator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initViews()
    }

    private fun initViews() {
        btnLogin = findViewById(R.id.fragment_register_btnLogin)
        registerDate = findViewById(R.id.register_date)

        registerEmail = findViewById(R.id.register_edit_email)
        registerPassword = findViewById(R.id.register_edit_password)
        registerPhoneNumber = findViewById(R.id.register_edit_phone)
        indicator = findViewById(R.id.register_progress_indicator)

        btnLogin!!.setOnClickListener(this)
        registerDate!!.setOnClickListener(this)
    }

    private fun startLoginFragment() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun pickInstallDate() {
        val now = Calendar.getInstance()
        val dpd = DatePickerDialog.newInstance(
                this@SignupActivity,
                now[Calendar.YEAR],  // Initial year selection
                now[Calendar.MONTH],  // Initial month selection
                now[Calendar.DAY_OF_MONTH] // Inital day selection
        )
        // If you're calling this from a support Fragment
//        dpd.show(getFragmentManager(), "Datepickerdialog");
// If you're calling this from an AppCompatActivity
        dpd.show(supportFragmentManager, "Datepickerdialog")
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fragment_register_btnLogin -> startLoginFragment()
            R.id.register_date -> pickInstallDate()
            R.id.register_button -> registerUser()
        }
    }

    private fun registerUser() {
        val userEmail = textEdit2String(registerEmail)
        val userPassword = textEdit2String(registerPassword)
        val userPhoneNumber = textEdit2String(registerPhoneNumber)
        val userInstallationDate = textEdit2String(registerDate)


            firebaseA




    }

    override fun onDateSet(view: DatePickerDialog, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val date = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year
        registerDate!!.setText(date)
    }

}