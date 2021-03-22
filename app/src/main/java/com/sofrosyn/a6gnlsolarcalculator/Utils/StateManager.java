package com.sofrosyn.a6gnlsolarcalculator.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class StateManager {


    private static final String SHARED_PREF_NAME = "offgrid";

    private static final String KEY_LOAD = "load";
    private static final String KEY_INVERTER_RATING = "inverter_rating";
    private static final String KEY_INVERTER_VOLTAGE = "inverter_voltage";
    private static final String KEY_NO_SOLAR_PANEL = "solarPanel";
    private static final String KEY_BACK_UP_TIME = "backup_time";
    private static final String KEY_TIME2CHARGE = "time2charge";
    private static final String KEY_BATTERY_SELECTED_VOLTAGE = "battery_voltage";
    private static final String KEY_BATTERY_AMP = "battery_amp";
    private static final String KEY_BATTERY_AMOUNT = "battery_amount";
    private static final String KEY_CHARGE_CONTROLLER = "charge_controller";
    private static final String KEY_POWER_RATING = "power_rating";

   /* private static final String LONGITUDE = "longitude";
    private static final String LATITUDE = "latitude";
    private static final String ZOOM = "zoom";
    private static final String BEARING = "bearing";
    private static final String TILT = "tilt";
*/

    private static StateManager mInstance;
    private static Context mCtx;

    private StateManager(Context context) {
        mCtx = context;
    }

    public static synchronized StateManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new StateManager(context);
        }
        return mInstance;
    }



    /*inverter fragment sharedpreference*/
    public void Inverter(int load,float inverterRating,float batteryBackUpTime,int selectedVoltage,float chargeTime,int  batteryAmp,int inverterVoltage,int batteryAmount) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putInt(KEY_LOAD, load);
        editor.putFloat(KEY_INVERTER_RATING, inverterRating);

        editor.putFloat(KEY_BACK_UP_TIME, batteryBackUpTime);
        editor.putInt(KEY_BATTERY_AMOUNT, batteryAmount);
        editor.putInt(KEY_BATTERY_SELECTED_VOLTAGE, selectedVoltage);
        editor.putFloat(KEY_TIME2CHARGE, chargeTime);
        editor.putInt(KEY_BATTERY_AMP, batteryAmp);
        editor.putInt(KEY_INVERTER_VOLTAGE, inverterVoltage);

        editor.apply();
    }


    public int getLoad() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_LOAD, 0);
    }


    public float getRatingKva() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(KEY_INVERTER_RATING, 0);
    }




    public float getBackUpTime() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(KEY_BACK_UP_TIME, 0);
    }

    public int getSelectedVoltage() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_BATTERY_SELECTED_VOLTAGE, 0);
    }


    public float getChargeTime() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(KEY_TIME2CHARGE, 0);
    }

    public int getBatteryAmp() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_BATTERY_AMP, 0);
    }


    public int getBatteryAmount() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_BATTERY_AMOUNT, 0);
    }
    public int getInverterVoltage() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_INVERTER_VOLTAGE, 0);
    }





    /*panel fragment sharedpreference*/
    public void Panels(float chargeController,int PowerRating,int panelAmount) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putFloat(KEY_CHARGE_CONTROLLER,chargeController);
        editor.putInt(KEY_POWER_RATING,PowerRating);
        editor.putInt(KEY_NO_SOLAR_PANEL,panelAmount);

        editor.apply();
    }


    public float getChargeController() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(KEY_CHARGE_CONTROLLER, 0);
    }
    public int getPanelCount() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_NO_SOLAR_PANEL, 0);
    }

    public int getPowerRating() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_POWER_RATING, 0);
    }


    //this method will logout the user
    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
       // mCtx.startActivity(new Intent(mCtx, LoginScreen.class));
    }

















}
