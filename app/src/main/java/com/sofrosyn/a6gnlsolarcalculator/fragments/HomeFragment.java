package com.sofrosyn.a6gnlsolarcalculator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.sofrosyn.a6gnlsolarcalculator.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class HomeFragment extends Fragment  {

   private View view;
   private MaterialCardView prInverter;
   private MaterialCardView prBattery;
   private MaterialCardView prPanels;
   private MaterialCardView prAbout;
    private CarouselView carouselView;


    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews();
        initAction();
        return view;
    }


    private void initViews(){
        int[] banners = {R.drawable.go_gree_go_solar,R.drawable.solar_vs_nepa1,R.drawable.solar_maintain};

        prInverter = view.findViewById(R.id.Fragment_home_inverter);
       prBattery = view.findViewById(R.id.Fragment_home_battery);
        prPanels = view.findViewById(R.id.Fragment_home_panels);
        prAbout = view.findViewById(R.id.Fragment_home_about);

        carouselView = view.findViewById(R.id.Fragment_home_carasoul);
        carouselView.setPageCount(banners.length);
        carouselView.setImageListener((position, imageView) -> {
            //imageView.setImageResource(banners[position]);
            Glide.with(getActivity()).asBitmap().load(banners[position]).into(imageView);
        });


    }


    private void initAction(){

        prInverter.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.inverterRatingFragment));
//        prBattery.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.batteryFragment));
  //      prPanels.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.panelFragment));
       prAbout.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.aboutUsFragment));

    }


}