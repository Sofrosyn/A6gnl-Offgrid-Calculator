package com.sofrosyn.a6gnlsolarcalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sofrosyn.a6gnlsolarcalculator.R

/**
 * A simple [Fragment] subclass.
 * Use the [AboutUsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutUsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }
}