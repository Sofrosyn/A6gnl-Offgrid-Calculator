package com.sofrosyn.a6gnlsolarcalculator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.print.PrintAttributes;
import android.text.TextUtils;
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
import com.uttampanchasara.pdfgenerator.CreatePdf;


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
        view = inflater.inflate(R.layout.fragment_panel, container, false);
        initView();

        return view;



    }



    private void initView(){

    printButton = view.findViewById(R.id.print_button);
    summaryText = view.findViewById(R.id.print_summary);

    summaryText.setText(printSummary());

    printButton.setOnClickListener(v -> printToPdf(printSummary()) );
    }




private String printSummary(){

            return String.format("Load : %sWatts \nInverter Rating: %sKva\nBattery Current: %s\nInverter Voltage: %sVolts\n" +
                "Number Of batteries: %s\nBattery Time to Charge: %sHrs\nBattery Backup Time: %sHrs\nSolar Panel Power Rating: %s\nNo. Solar Panels: %s\n" +
                "Charge Controller: %s\n",StateManager.getInstance(getActivity()).getLoad(),StateManager.getInstance(getActivity()).getRatingKva(),
                    StateManager.getInstance(getActivity()).getBatteryAmp(),StateManager.getInstance(getActivity()).getInverterVoltage(),
                    StateManager.getInstance(getActivity()).getBatteryAmount(),StateManager.getInstance(getActivity()).getChargeTime(),StateManager.getInstance(getActivity()).getBackUpTime(),
                    StateManager.getInstance(getActivity()).getPowerRating(),StateManager.getInstance(getActivity()).getLoad(),StateManager.getInstance(getActivity()).getChargeController());

}




private void printToPdf(String message){

    new CreatePdf(getActivity())
            .setPdfName("FirstPdf")
            .openPrintDialog(false)
            .setContentBaseUrl(null)
            .setPageSize(PrintAttributes.MediaSize.ISO_A4)
            .setContent(message)
            .setFilePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyPdf")
            .setCallbackListener(new CreatePdf.PdfCallbackListener() {
                @Override
                public void onFailure( String s) {
                    // handle error
                }

                @Override
                public void onSuccess( String s) {
                    // do your stuff here
                }
            })
            .create();

}
}