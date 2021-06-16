package com.sofrosyn.a6gnlsolarcalculator.logic

import java.text.DecimalFormat

object Calculator {
    private var inverterRatingKva = 0.0
    private const val backUpTime = 0
    private const val timeToCharge = 0
    private var minRatingChargeController = 0
    private var noSolarPanels = 0
    private const val LOADFACTOR = 0.8
    private const val AMPHOURBATTERY = 200
    private const val HOURS = 8



    fun loadInKva(load: Double): Double {
        inverterRatingKva = load / LOADFACTOR / 1000
        return inverterRatingKva
    }

    fun batteryBackupTime(load: Float, inverterVoltage: Float, batteryAmp: Float): Float {
        val cal = load / inverterVoltage
        return batteryAmp / cal
    }

    fun timeToCharge(batteryAmps: Float, inverterVoltage: Float): Float {
        return batteryAmps / inverterVoltage
    }

    fun minCurrentRatingController(panelPowerRating: Int, panelVoltage: Int, noSolarPanel: Int): Int {
        minRatingChargeController = panelPowerRating * noSolarPanel
        return minRatingChargeController / panelVoltage
    }

    fun totalPanels(inverterVoltage: Int, panelRating: Int): Int {
        val a = AMPHOURBATTERY * inverterVoltage
        val b = HOURS * panelRating
        noSolarPanels = a / b
        return noSolarPanels
    }

    fun ampsConverter(batterNumber: Int, divisor: Int, ampHourBattery: Int): Int {
        val base = batterNumber / divisor
        return base * ampHourBattery
    }

    fun KvaMargin(load: Double): Double {
        return load + 0.3 * load
    }

    fun formatDecimal(value: Double): String {
        val decimalFormat = DecimalFormat("#.##")
        return decimalFormat.format(value)
    }

    }

