package com.sofrosyn.a6gnlsolarcalculator.fragments

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.sofrosyn.a6gnlsolarcalculator.R
import com.sofrosyn.a6gnlsolarcalculator.Utils.StateManager
import com.sofrosyn.a6gnlsolarcalculator.logic.Calculator.ampsConverter
import com.sofrosyn.a6gnlsolarcalculator.logic.Calculator.batteryBackupTime
import com.sofrosyn.a6gnlsolarcalculator.logic.Calculator.formatDecimal
import com.sofrosyn.a6gnlsolarcalculator.logic.Calculator.loadInKva
import com.sofrosyn.a6gnlsolarcalculator.logic.Calculator.timeToCharge
import java.util.*

import java.util.stream.IntStream

class InverterRatingFragment : Fragment() ,View.OnClickListener {
    private var inverterLoad: TextInputEditText? = null
    private var batteryNumber: TextInputEditText? = null
    private var estInverterRating: MaterialTextView? = null

    private var batteryChips: ChipGroup? = null
    private var inverterVoltageChip: ChipGroup? = null
    //private var view: View? = null
    private var inverterNext: MaterialButton? = null
    private var calculate: MaterialButton? = null
    private var acceptedBatteryCount: ArrayList<Int>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
      val  view = inflater.inflate(R.layout.fragment_inverter_rating, container, false)
        initViews()
        initActions()
        return view
    }

    private fun initViews() {
        inverterLoad = view?.findViewById(R.id.fragment_inverter_load)
        // batteryLoad = view.findViewById(R.id.fragment_battery_load);
        estInverterRating = view?.findViewById(R.id.fragment_inverter_load_text)
        inverterVoltageChip = view?.findViewById(R.id.inverter_voltage)
        inverterNext = view?.findViewById(R.id.inverter_next)
        batteryNumber = view?.findViewById(R.id.fragment_inverter_battery_no)
        calculate = view?.findViewById(R.id.inverter_calculate)
//        waveLoadingView = view?.findViewById<>(R.id.waveLoadingView)
        batteryChips = view?.findViewById(R.id.chips_battery)
        acceptedBatteryCount = ArrayList()
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private fun initActions() {
        inverterLoad!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (start > 1) {
                    ratingKva = loadInKva(s.toString().toDouble())
                    estInverterRating!!.text = String.format("Your Estimated Inverter Rating is [%sKva] ", ratingKva)
                } else {
                    estInverterRating!!.text = "Your Estimated Inverter Rating  [Kva] "
  //                  waveLoadingView!!.centerTitle = "Load"
                }
            }

            override fun afterTextChanged(s: Editable) {
    //            waveLoadingView!!.progressValue = ratingKva.toInt() * 10
                estInverterRating!!.setTextColor(resources.getColor(R.color.colorPrimaryDark))
      //          waveLoadingView!!.centerTitle = "Inverter: " + ratingKva + "Kva"
            }
        })
        inverterNext!!.setOnClickListener { v: View? ->
            val load = inverterLoad!!.text.toString().toInt()
            val batteryCount = batteryNumber!!.text.toString().toInt()
            StateManager.getInstance(activity)
                    .Inverter(load, ratingKva.toFloat(), backUpTime.toFloat(), invertVoltage, time2Charge.toFloat(), batteryAmp, invertVoltage, batteryCount)
            //Navigation.findNavController(view?).navigate(R.id.action_inverterRatingFragment_to_batteryFragment)
           // it.findNavController().navigate(EmployeesListFragmentDirections.actionEmployeesListFragmentToAddEmployeeFragment())



        }
        batteryChips!!.setOnCheckedChangeListener { group: ChipGroup, checkedId: Int ->
            val chip: Chip = group.findViewById(checkedId)
            if (chip != null) {
                batteryAmp = chip.text.toString().toInt()
                Toast.makeText(activity, "Battery Amp: $batteryAmp", Toast.LENGTH_SHORT).show()
            }
        }
        inverterVoltageChip!!.setOnCheckedChangeListener { group: ChipGroup, checkedId: Int ->
            val chip: Chip = group.findViewById(checkedId)
            if (chip != null) {
                invertVoltage = chip.text.toString().toInt()
                Toast.makeText(activity, "Inverter Voltage: $invertVoltage", Toast.LENGTH_SHORT).show()
                when (invertVoltage) {
                    12 -> {
                        divisor = 1
                        acceptedBatteryCount!!.clear()
                        acceptedBatteryCount!!.add(IntStream.rangeClosed(1, 10).iterator().nextInt())
                        Log.d("Divisor", " $divisor")
                    }
                    24 -> {
                        divisor = 2
                        acceptedBatteryCount!!.clear()
                        IntStream.range(2, 20).filter { x: Int -> x % 2 == 0 }.forEach { x: Int -> acceptedBatteryCount!!.add(x) }
                        Log.d("Divisor", " $divisor")
                    }
                    48 -> {
                        divisor = 4
                        acceptedBatteryCount!!.clear()
                        IntStream.range(4, 20).filter { x: Int -> x % 4 == 0 }.forEach { x: Int -> acceptedBatteryCount!!.add(x) }
                        Log.d("Divisor", " $divisor")
                    }
                }
            }
        }
        calculate!!.setOnClickListener {
            if (!validate()) {
                return@setOnClickListener
            } else {
                val totalBatteries = batteryNumber!!.text.toString().toInt()
                val batteryAmps = ampsConverter(totalBatteries, divisor, batteryAmp)
                val load = inverterLoad!!.text.toString().toInt()
                time2Charge = timeToCharge(batteryAmps.toFloat(), invertVoltage.toFloat()).toDouble()
                backUpTime = batteryBackupTime(load.toFloat(), invertVoltage.toFloat(), batteryAmps.toFloat()).toDouble()
        //        waveLoadingView!!.topTitle = "Charge Time: " + formatDecimal(time2Charge) + "hrs"
          //      waveLoadingView!!.bottomTitle = "BackUp Time: " + formatDecimal(backUpTime) + "hrs"
            }
        }
    }

    private fun validate(): Boolean {
        if (TextUtils.isEmpty(inverterLoad!!.text) || TextUtils.isEmpty(batteryNumber!!.text)) {
            Toast.makeText(activity, "Fill in Values", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!acceptedBatteryCount!!.contains(batteryNumber!!.text.toString().toInt())) {
            Toast.makeText(activity, "Accepted Battery Count $acceptedBatteryCount", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    companion object {
        private var batteryAmp = 100
        private var invertVoltage = 12
        private var divisor = 1
        private var ratingKva = 0.0
        private var time2Charge = 0.0
        private var backUpTime = 0.0
    }

    override fun onClick(view: View) {
        val action =
                InverterRatingFragmentDirections.actionInverterRatingFragmentToBatteryFragment()
        view.findNavController().navigate(action)
    }

}