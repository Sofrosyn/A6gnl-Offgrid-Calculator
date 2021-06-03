package com.sofrosyn.a6gnlsolarcalculator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sofrosyn.a6gnlsolarcalculator.R;
import com.sofrosyn.a6gnlsolarcalculator.adapter.NotificationAdapter;
import com.sofrosyn.a6gnlsolarcalculator.modals.Notification;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {
private RecyclerView recyclerView;
private NotificationAdapter notificationAdapter;
private ArrayList<Notification> notificationArrayList;
private View view;

    public NotificationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_notification, container, false);

         initViews();
         return view;
    }

    private void initViews() {

        recyclerView = view.findViewById(R.id.notification_fragment_recyclerview);
        notificationArrayList = new ArrayList<>();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
    }


    private void initAction(){

      Notification notification1 = new Notification("Scheduled Visitation","Please be advised your next maintenance visit is scheduled for the 14th June, 2021",2000);
      Notification notification2 = new Notification("Solar system Self care","It's going to be cloudy today, try to reduce the total load on your system",2000);
      Notification notification3 = new Notification("Scheduled Visitation","Please be advised your next maintenance visit is scheduled for the 14th July, 2021",2000);
      Notification notification4 = new Notification("Solar system Self care","Today is projected to be rainy please keep the load on your system light",2000);

      notificationArrayList.add(notification1);
      notificationArrayList.add(notification2);
      notificationArrayList.add(notification3);
      notificationArrayList.add(notification4);

      notificationAdapter = new NotificationAdapter(getContext(),notificationArrayList);
      recyclerView.setAdapter(notificationAdapter);




    }

    @Override
    public void onStart() {
        super.onStart();
        initAction();
    }
}