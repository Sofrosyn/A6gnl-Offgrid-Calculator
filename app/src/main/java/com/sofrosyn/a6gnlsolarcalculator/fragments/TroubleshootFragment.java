package com.sofrosyn.a6gnlsolarcalculator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;
import com.sofrosyn.a6gnlsolarcalculator.R;
import com.sofrosyn.a6gnlsolarcalculator.adapter.NotificationAdapter;
import com.sofrosyn.a6gnlsolarcalculator.adapter.TroubleshootAdapter;
import com.sofrosyn.a6gnlsolarcalculator.modals.Troubleshoot;

import java.util.ArrayList;

public class TroubleshootFragment extends Fragment {

    private View view;
    private MaterialCardView cardView;
    private RecyclerView recyclerView;
    private ArrayList<Troubleshoot> troubleshootArrayList;
    private TroubleshootAdapter troubleshootAdapter;
    public TroubleshootFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_troubleshoot, container, false);
        initViews();
        return view;
    }

    private void initViews() {


        recyclerView = view.findViewById(R.id.fragment_troubleshoot_recyclerview);
        troubleshootArrayList = new ArrayList<>();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
    }


        private void initAction(){

        Troubleshoot troubleshoot1 = new Troubleshoot("How to fix slow charging","Easily fix slow charging on your solar inverter system","");
        Troubleshoot troubleshoot2 = new Troubleshoot("How to fix low current","Easily fix slow charging on your solar inverter system","");
        Troubleshoot troubleshoot3 = new Troubleshoot("How to fix not charging inverter","Easily fix slow charging on your solar inverter system","");


        troubleshootArrayList.add(troubleshoot1);
        troubleshootArrayList.add(troubleshoot2);
        troubleshootArrayList.add(troubleshoot3);


        troubleshootAdapter = new TroubleshootAdapter(getContext(),troubleshootArrayList);
        recyclerView.setAdapter(troubleshootAdapter);


    }

    @Override
    public void onStart() {
        super.onStart();
        initAction();
    }


}