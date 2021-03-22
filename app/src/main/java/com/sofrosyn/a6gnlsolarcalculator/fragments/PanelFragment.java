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
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.sofrosyn.a6gnlsolarcalculator.R;
import com.sofrosyn.a6gnlsolarcalculator.Utils.StateManager;
import com.sofrosyn.a6gnlsolarcalculator.logic.Calculator;

public class PanelFragment extends Fragment {

private ChipGroup voltageGroup;
private TextInputEditText panelRating;
private TextInputEditText panelNumber;
private MaterialTextView summary;
private MaterialTextView prText;
private IconRoundCornerProgressBar prChargeController;
//private IconRoundCornerProgressBar prTimeToCharge;

private MaterialButton Calculate;
private MaterialButton batteryNext;


private View  view ;

private static float valBackUpTime;
private static int valChargeController;
private static int valSelectedVoltage = 12;


    public PanelFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_battery, container, false);
        initViews();
        initActions();
        return view;
    }

    private void initActions() {







        batteryNext.setOnClickListener(v -> {
            int varRatings = Integer.parseInt(panelRating.getText().toString());
            int varPanelNumber = Integer.parseInt(panelNumber.getText().toString());
         StateManager.getInstance(getActivity()).Panels(valChargeController,varRatings,varPanelNumber);
            Navigation.findNavController(view).navigate(R.id.action_batteryFragment_to_panelFragment);

        });





    }


    private void initViews(){


         voltageGroup = view.findViewById(R.id.battery_inverter_voltage);
        Calculate = view.findViewById(R.id.fragment_panel_calculate);
        panelNumber = view.findViewById(R.id.fragment_panel_no_panel);
        panelRating = view.findViewById(R.id.fragment_panel_rating);
        prChargeController = view.findViewById(R.id.battery_progress_charge);
       prText = view.findViewById(R.id.text_chargeController);


        batteryNext = view.findViewById(R.id.battery_next);



        voltageGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Chip chip = group.findViewById(checkedId);
            if(chip!= null){
                valSelectedVoltage = Integer.parseInt(chip.getText().toString());
                Log.d("Selected Voltage", " "+valSelectedVoltage);

                Toast.makeText(getActivity(), "Selected Inverter Voltage: "+valSelectedVoltage, Toast.LENGTH_SHORT).show();

            }
        });


        Calculate.setOnClickListener(v -> calcChargeController());



    }


    private void calcChargeController(){
int  valPanelRating = Integer.parseInt(panelRating.getText().toString());
int  valPanelNumber = Integer.parseInt(panelNumber.getText().toString());
if (TextUtils.isEmpty(panelNumber.getText()) || TextUtils.isEmpty(panelRating.getText())){
    Toast.makeText(getActivity(), "Fill all fields", Toast.LENGTH_SHORT).show();
}else {


    valChargeController =  Calculator.minCurrentRatingController(valPanelRating,valSelectedVoltage,valPanelNumber);
    prChargeController.setProgress(valChargeController);
    prText.setText("Min Charge Controller: "+valChargeController);

}


    }







}