package com.sofrosyn.a6gnlsolarcalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sofrosyn.a6gnlsolarcalculator.R
import com.sofrosyn.a6gnlsolarcalculator.adapter.NotificationAdapter
import com.sofrosyn.a6gnlsolarcalculator.modals.Notification
import java.util.*

class NotificationFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var notificationAdapter: NotificationAdapter? = null
    private var notificationArrayList: ArrayList<Notification>? = null
   // private var view: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_notification, container, false)
        initViews()
        return view
    }

    private fun initViews() {
        recyclerView = view?.findViewById(R.id.notification_fragment_recyclerview)
        notificationArrayList = ArrayList()
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }

    private fun initAction() {
        val notification1 = Notification("Scheduled Visitation", "Please be advised your next maintenance visit is scheduled for the 14th June, 2021", 2000)
        val notification2 = Notification("Solar system Self care", "It's going to be cloudy today, try to reduce the total load on your system", 2000)
        val notification3 = Notification("Scheduled Visitation", "Please be advised your next maintenance visit is scheduled for the 14th July, 2021", 2000)
        val notification4 = Notification("Solar system Self care", "Today is projected to be rainy please keep the load on your system light", 2000)
        notificationArrayList!!.add(notification1)
        notificationArrayList!!.add(notification2)
        notificationArrayList!!.add(notification3)
        notificationArrayList!!.add(notification4)
        notificationAdapter = context?.let { NotificationAdapter(it, notificationArrayList!!) }
        recyclerView!!.adapter = notificationAdapter
    }

    override fun onStart() {
        super.onStart()
        initAction()
    }
}