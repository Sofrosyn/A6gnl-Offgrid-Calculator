package com.sofrosyn.a6gnlsolarcalculator.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
//import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.sofrosyn.a6gnlsolarcalculator.R
import com.sofrosyn.a6gnlsolarcalculator.Utils.StateManager
import com.sofrosyn.a6gnlsolarcalculator.logic.Calculator.minCurrentRatingController

class PanelFragment : Fragment(), View.OnClickListener {
    private var voltageGroup: ChipGroup? = null
    private var panelRating: TextInputEditText? = null
    private var panelNumber: TextInputEditText? = null
    private val summary: MaterialTextView? = null
    private var prText: MaterialTextView? = null
   // private var prChargeController: IconRoundCornerProgressBar? = null

    //private IconRoundCornerProgressBar prTimeToCharge;
    private var Calculate: MaterialButton? = null
    private var batteryNext: MaterialButton? = null
   // private var view: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
     val   view = inflater.inflate(R.layout.fragment_battery, container, false)
        initViews()
        initActions()
        return view
    }

    private fun initActions() {
        batteryNext!!.setOnClickListener {
            val varRatings = panelRating!!.text.toString().toInt()
            val varPanelNumber = panelNumber!!.text.toString().toInt()
            StateManager.getInstance(activity).Panels(valChargeController.toFloat(), varRatings, varPanelNumber)
            //Navigation.findNavController(view!!).navigate(R.id.action_batteryFragment_to_panelFragment)
            //it.findNavController().navigate()

        }
    }

    private fun initViews() {
        voltageGroup = view?.findViewById(R.id.battery_inverter_voltage)
        Calculate = view?.findViewById(R.id.fragment_panel_calculate)
        panelNumber = view?.findViewById(R.id.fragment_panel_no_panel)
        panelRating = view?.findViewById(R.id.fragment_panel_rating)
       // prChargeController = view?.findViewById(R.id.battery_progress_charge)
      //  prText = view?.findViewById(R.id.text_chargeController)
        batteryNext = view?.findViewById(R.id.battery_next)
        voltageGroup?.setOnCheckedChangeListener { group: ChipGroup, checkedId: Int ->
            val chip: Chip = group.findViewById(checkedId)
            if (chip != null) {
                valSelectedVoltage = chip.text.toString().toInt()
                Log.d("Selected Voltage", " $valSelectedVoltage")
                Toast.makeText(activity, "Selected Inverter Voltage: $valSelectedVoltage", Toast.LENGTH_SHORT).show()
            }
        }
        Calculate?.setOnClickListener { calcChargeController() }
    }

    private fun calcChargeController() {
        val valPanelRating = panelRating!!.text.toString().toInt()
        val valPanelNumber = panelNumber!!.text.toString().toInt()
        if (TextUtils.isEmpty(panelNumber!!.text) || TextUtils.isEmpty(panelRating!!.text)) {
            Toast.makeText(activity, "Fill all fields", Toast.LENGTH_SHORT).show()
        } else {
            valChargeController = minCurrentRatingController(valPanelRating, valSelectedVoltage, valPanelNumber)
        //    prChargeController!!.setProgress(valChargeController)
            prText!!.text = "Min Charge Controller: $valChargeController"
        }
    }

    override fun onClick(v: View?) {
        //val action = Directions
    }



    companion object {
        private const val valBackUpTime = 0f
        private var valChargeController = 0
        private var valSelectedVoltage = 12
    }


}