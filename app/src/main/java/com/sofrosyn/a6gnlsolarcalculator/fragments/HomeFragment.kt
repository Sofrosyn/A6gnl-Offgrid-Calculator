package com.sofrosyn.a6gnlsolarcalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.sofrosyn.a6gnlsolarcalculator.R

class HomeFragment : Fragment() {
    //private var view: View? = null
    private var prInverter: MaterialCardView? = null
    private var prNotification: MaterialCardView? = null
    private var prTroubleshoot: MaterialCardView? = null
    private var prAbout: MaterialCardView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initViews()
        initAction()
        return view
    }

    private fun initViews() {
        val banners = intArrayOf(R.drawable.go_gree_go_solar, R.drawable.solar_vs_nepa1, R.drawable.solar_maintain)
        prInverter = view?.findViewById(R.id.Fragment_home_inverter)
        prNotification = view?.findViewById(R.id.Fragment_notification)
        prTroubleshoot = view?.findViewById(R.id.Fragment_trouble_shoot)
        prAbout = view?.findViewById(R.id.Fragment_home_about)

    }

    private fun initAction() {
        prInverter!!.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.inverterRatingFragment))
        prAbout!!.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.aboutUsFragment))
        prTroubleshoot!!.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.troubleshootFragment))
        prNotification!!.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.notificationFragment))
    }
}