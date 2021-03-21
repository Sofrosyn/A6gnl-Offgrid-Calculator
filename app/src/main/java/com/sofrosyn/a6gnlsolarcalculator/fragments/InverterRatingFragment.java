package com.sofrosyn.a6gnlsolarcalculator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.sofrosyn.a6gnlsolarcalculator.R;
import com.sofrosyn.a6gnlsolarcalculator.Utils.StateManager;
import com.sofrosyn.a6gnlsolarcalculator.logic.Calculator;

import java.util.List;

import me.itangqi.waveloadingview.WaveLoadingView;


public class InverterRatingFragment extends Fragment {


    private TextInputEditText inverterLoad;
    private TextInputEditText batteryNumber;
    private MaterialTextView estInverterRating;

    private WaveLoadingView waveLoadingView;
    private ChipGroup batteryChips;
    private ChipGroup inverterVoltageChip;
    private View view;
    private MaterialButton inverterNext;
    private MaterialButton calculate;
    private static int batteryAmp = 100;
    private static int invertVoltage = 12;
    private static int divisor = 1;


    private static double ratingKva;
    private static double time2Charge;
    private static double backUpTime;


    public InverterRatingFragment() {



    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inverter_rating, container, false);
         initViews();
         initActions();
         return view;
    }



    private void initViews(){


        inverterLoad = view.findViewById(R.id.fragment_inverter_load);
       // batteryLoad = view.findViewById(R.id.fragment_battery_load);

        estInverterRating = view.findViewById(R.id.fragment_inverter_load_text);
        inverterVoltageChip = view.findViewById(R.id.inverter_voltage);
        inverterNext = view.findViewById(R.id.inverter_next);
        batteryNumber = view.findViewById(R.id.fragment_inverter_battery_no);
        calculate = view.findViewById(R.id.inverter_calculate);
        waveLoadingView =view.findViewById(R.id.waveLoadingView);
        batteryChips = view.findViewById(R.id.chips_battery);

    }


    private void initActions(){


        inverterLoad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(start > 1){
                ratingKva  =  Calculator.loadInKva(Double.parseDouble(String.valueOf(s)));
                estInverterRating.setText(String.format("Your Estimated Inverter Rating is [%sKva] ",ratingKva));
                }else {
                    estInverterRating.setText("Your Estimated Inverter Rating  [Kva] ");
                    waveLoadingView.setCenterTitle("Load");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                waveLoadingView.setProgressValue((int) ratingKva* 10);
                estInverterRating.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                waveLoadingView.setCenterTitle("Inverter: "+ratingKva+"Kva");

            }
        });



        inverterNext.setOnClickListener(v -> {
            int load = Integer.parseInt(inverterLoad.getText().toString());
            int batteryCount = Integer.parseInt(batteryNumber.getText().toString());


            StateManager.getInstance(getActivity())
                    .Inverter(load,(float) ratingKva,(float) backUpTime,invertVoltage,(float)time2Charge,batteryAmp,invertVoltage,batteryCount);
            Navigation.findNavController(view).navigate(R.id.action_inverterRatingFragment_to_batteryFragment);

        });



        batteryChips.setOnCheckedChangeListener((group, checkedId) -> {
    Chip chip = group.findViewById(checkedId);
     if(chip != null){
         batteryAmp = Integer.parseInt(chip.getText().toString());
         Toast.makeText(getActivity(), "Battery Amp: "+batteryAmp, Toast.LENGTH_SHORT).show();
     } });



        inverterVoltageChip.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            if(chip != null){
                invertVoltage = Integer.parseInt(chip.getText().toString());
                Toast.makeText(getActivity(), "Inverter Voltage: "+invertVoltage, Toast.LENGTH_SHORT).show();

                switch (invertVoltage){
                    case 12:
                        divisor = 1;
                        Log.d("Divisor", " "+ divisor);
                        break;
                    case 24:
                        divisor = 2;
                        Log.d("Divisor", " "+ divisor);
                        break;
                    case 48 :
                        divisor = 4;
                        Log.d("Divisor", " "+ divisor);
                        break;
                }

            } });





   calculate.setOnClickListener(v -> {
    if (TextUtils.isEmpty(inverterLoad.getText()) || TextUtils.isEmpty(batteryNumber.getText())){
        Toast.makeText(getActivity(), "Fill in Values", Toast.LENGTH_SHORT).show();
        return;
    }else {

        int totalBatteries = Integer.parseInt(batteryNumber.getText().toString());
        int batteryAmps = Calculator.ampsConverter(totalBatteries, divisor, batteryAmp);
        int load = Integer.parseInt(inverterLoad.getText().toString());
        time2Charge = Calculator.timeToCharge(batteryAmps, invertVoltage);
        backUpTime = Calculator.batteryBackupTime(load, invertVoltage, batteryAmps);

        waveLoadingView.setTopTitle("Charge Time: " + Calculator.formatDecimal(time2Charge) + "hrs");
        waveLoadingView.setBottomTitle("BackUp Time: " + Calculator.formatDecimal(backUpTime) + "hrs");
    }

   });

    }








}