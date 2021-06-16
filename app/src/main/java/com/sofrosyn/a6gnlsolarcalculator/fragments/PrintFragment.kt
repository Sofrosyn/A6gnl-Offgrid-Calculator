package com.sofrosyn.a6gnlsolarcalculator.fragments

import android.os.Bundle
import android.print.PrintAttributes
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.sofrosyn.a6gnlsolarcalculator.R
import com.sofrosyn.a6gnlsolarcalculator.Utils.StateManager
import com.sofrosyn.a6gnlsolarcalculator.logic.Calculator.KvaMargin
import java.util.*

class PrintFragment : Fragment() {
    private var printButton: MaterialButton? = null
    private var summaryText: TextView? = null
  //  private var view: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
    val    view = inflater.inflate(R.layout.fragment_print, container, false)
        initView()
        return view
    }

    private fun initView() {
        printButton = view?.findViewById(R.id.print_button)
        summaryText = view?.findViewById(R.id.print_summary)
        summaryText?.text = showSummary()
        printButton?.setOnClickListener { printToPdf(printSummary()) }
    }

    private fun showSummary(): String {
        return String.format("""
    Load : 	%sWatts 
    
    <br>30 Percent Inverter: 	%sKva 
    
    <br>Inverter Rating: 	%sKva
    
    Battery Current: 	%sA
    
    Inverter Voltage: 	%sVolts
    
    Number Of batteries: 	%s
    
    Battery Time to Charge: 	%sHrs
    
    Battery Backup Time: 	%sHrs
    
    Solar Panel Power Rating: 	%s
    
    No. Solar Panels: 	%s
    
    Charge Controller: 	%s
    
    
    """.trimIndent(), StateManager.getInstance(activity).load, KvaMargin(StateManager.getInstance(activity).ratingKva.toDouble()), StateManager.getInstance(activity).ratingKva,
                StateManager.getInstance(activity).batteryAmp, StateManager.getInstance(activity).inverterVoltage,
                StateManager.getInstance(activity).batteryAmount, StateManager.getInstance(activity).chargeTime, StateManager.getInstance(activity).backUpTime,
                StateManager.getInstance(activity).powerRating, StateManager.getInstance(activity).panelCount, StateManager.getInstance(activity).chargeController)
    }

    private fun printSummary(): String {
        return String.format("A6GNL OFF-GRID SOLAR CALCULATOR<br><br>Load : \t%sWatts <br>30 Percent Inverter: \t%sKva<br> Inverter Rating: \t%sKva<br>Battery Current: \t%sA<br>Inverter Voltage: \t%sVolts<br>" +
                "Number Of batteries: \t%s<br>Battery Time to Charge: \t%sHrs<br>Battery Backup Time: \t%sHrs<br>Solar Panel Power Rating: \t%s<br>No. Solar Panels: \t%s<br>" +
                "Charge Controller: \t%s<br>", StateManager.getInstance(activity).load, KvaMargin(StateManager.getInstance(activity).ratingKva.toDouble()), StateManager.getInstance(activity).ratingKva,
                StateManager.getInstance(activity).batteryAmp, StateManager.getInstance(activity).inverterVoltage,
                StateManager.getInstance(activity).batteryAmount, StateManager.getInstance(activity).chargeTime, StateManager.getInstance(activity).backUpTime,
                StateManager.getInstance(activity).powerRating, StateManager.getInstance(activity).panelCount, StateManager.getInstance(activity).chargeController)
    }

    private fun printToPdf(message: String) {
        val rand = Random(584523)
        val key = rand.nextInt()


    }
}