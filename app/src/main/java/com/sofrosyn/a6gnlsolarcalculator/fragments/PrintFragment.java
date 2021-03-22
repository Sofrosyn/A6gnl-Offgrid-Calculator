package com.sofrosyn.a6gnlsolarcalculator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.print.PrintAttributes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.sofrosyn.a6gnlsolarcalculator.R;
import com.sofrosyn.a6gnlsolarcalculator.Utils.StateManager;
import com.sofrosyn.a6gnlsolarcalculator.logic.Calculator;
import com.uttampanchasara.pdfgenerator.CreatePdf;

import java.util.Random;


public class PrintFragment extends Fragment {

  MaterialButton printButton;
  TextView summaryText;
    private View view ;



    public PrintFragment() {
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
        view = inflater.inflate(R.layout.fragment_print, container, false);
        initView();

        return view;



    }



    private void initView(){

    printButton = view.findViewById(R.id.print_button);
    summaryText = view.findViewById(R.id.print_summary);

    summaryText.setText(showSummary());

    printButton.setOnClickListener(v -> printToPdf(printSummary()) );
    }




private String showSummary(){

            return String.format("Load : \t%sWatts \n\n<br>30 Percent Inverter: \t%sKva \n\n<br>Inverter Rating: \t%sKva\n\nBattery Current: \t%sA\n\nInverter Voltage: \t%sVolts\n\n" +
                "Number Of batteries: \t%s\n\nBattery Time to Charge: \t%sHrs\n\nBattery Backup Time: \t%sHrs\n\nSolar Panel Power Rating: \t%s\n\nNo. Solar Panels: \t%s\n\n" +
                "Charge Controller: \t%s\n\n",StateManager.getInstance(getActivity()).getLoad(), Calculator.KvaMargin(StateManager.getInstance(getActivity()).getRatingKva()), StateManager.getInstance(getActivity()).getRatingKva(),
                    StateManager.getInstance(getActivity()).getBatteryAmp(),StateManager.getInstance(getActivity()).getInverterVoltage(),
                    StateManager.getInstance(getActivity()).getBatteryAmount(),StateManager.getInstance(getActivity()).getChargeTime(),StateManager.getInstance(getActivity()).getBackUpTime(),
                    StateManager.getInstance(getActivity()).getPowerRating(),StateManager.getInstance(getActivity()).getPanelCount(),StateManager.getInstance(getActivity()).getChargeController());

}

    private String printSummary(){

        return String.format("A6GNL OFF-GRID SOLAR CALCULATOR<br><br>Load : \t%sWatts <br>30 Percent Inverter: \t%sKva<br> Inverter Rating: \t%sKva<br>Battery Current: \t%sA<br>Inverter Voltage: \t%sVolts<br>" +
                        "Number Of batteries: \t%s<br>Battery Time to Charge: \t%sHrs<br>Battery Backup Time: \t%sHrs<br>Solar Panel Power Rating: \t%s<br>No. Solar Panels: \t%s<br>" +
                        "Charge Controller: \t%s<br>",StateManager.getInstance(getActivity()).getLoad(), Calculator.KvaMargin(StateManager.getInstance(getActivity()).getRatingKva()), StateManager.getInstance(getActivity()).getRatingKva(),
                StateManager.getInstance(getActivity()).getBatteryAmp(),StateManager.getInstance(getActivity()).getInverterVoltage(),
                StateManager.getInstance(getActivity()).getBatteryAmount(),StateManager.getInstance(getActivity()).getChargeTime(),StateManager.getInstance(getActivity()).getBackUpTime(),
                StateManager.getInstance(getActivity()).getPowerRating(),StateManager.getInstance(getActivity()).getPanelCount(),StateManager.getInstance(getActivity()).getChargeController());

    }



private void printToPdf(String message){
    Random rand = new Random(584523);
    int key = rand.nextInt();
    new CreatePdf(getActivity())
            .setPdfName("A6GNL_OFFGRID"+key)
            .openPrintDialog(true)
            .setPageSize(PrintAttributes.MediaSize.ISO_A4)
            .setContent(message)
         //   .setFilePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyPdf")
            .setCallbackListener(new CreatePdf.PdfCallbackListener() {
                @Override
                public void onFailure( String s) {
                    Log.d("Print", s);
                }

                @Override
                public void onSuccess( String s) {
                    Log.d("Print", s);
                }
            })
            .create();

}
}