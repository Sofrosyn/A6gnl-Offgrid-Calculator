package com.sofrosyn.a6gnlsolarcalculator.logic;

import java.text.DecimalFormat;

public  class Calculator {

  private static double inverterRatingKva;
  private static int backUpTime;
  private static int timeToCharge;
  private static int minRatingChargeController;
  private static int noSolarPanels;

  private static final double LOADFACTOR = 0.8;
  private static final int AMPHOURBATTERY = 200;
  private static final int HOURS = 8;



  public static double loadInKva(double load ){

      inverterRatingKva = (load / LOADFACTOR)/ 1000;
        return inverterRatingKva;
      }


      public static float batteryBackupTime(float load, float inverterVoltage,float batteryAmp){
         float cal = load / inverterVoltage;
          return batteryAmp / cal;

      }

      public static float timeToCharge(float batteryAmps,float inverterVoltage){
      return batteryAmps / inverterVoltage;
      }


      public static int minCurrentRatingController(int panelPowerRating,int panelVoltage, int noSolarPanel){

      minRatingChargeController  = panelPowerRating * noSolarPanel;
      return minRatingChargeController / panelVoltage;
  }


      public static int totalPanels(int inverterVoltage, int panelRating){

    int a = AMPHOURBATTERY * inverterVoltage;
    int b = HOURS  * panelRating;
          noSolarPanels = a / b;
      return noSolarPanels;
      }


      public static int ampsConverter(int batterNumber,int divisor,int ampHourBattery){

      int base = batterNumber / divisor;
      return base * ampHourBattery;


      }

    public static double KvaMargin(double load ){


        return load + 0.3*load;
    }



    public static String formatDecimal(double value){
          DecimalFormat decimalFormat = new DecimalFormat("#.##");
            return decimalFormat.format(value);

      }



}
