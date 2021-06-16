package com.sofrosyn.a6gnlsolarcalculator.Utils

import android.app.Activity
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import es.dmoral.toasty.Toasty

object Extensions {

    //call toast from any where
    fun Activity.toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun Activity.error(msg: String){
        Toasty.error(this, msg, Toast.LENGTH_SHORT, true).show();

    }

    fun Activity.success(msg: String){
        Toasty.success(this, msg, Toast.LENGTH_SHORT).show()
    }


    fun Activity.info(msg: String){
        Toasty.info(this, msg, Toast.LENGTH_SHORT).show()
    }


    fun textEdit2String(text: TextInputEditText?): String{
        return text?.text?.trim().toString()
    }



}