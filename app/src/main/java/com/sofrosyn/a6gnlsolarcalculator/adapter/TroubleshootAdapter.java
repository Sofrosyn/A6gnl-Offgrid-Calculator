package com.sofrosyn.a6gnlsolarcalculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.sofrosyn.a6gnlsolarcalculator.R;
import com.sofrosyn.a6gnlsolarcalculator.modals.Notification;
import com.sofrosyn.a6gnlsolarcalculator.modals.Troubleshoot;

import java.util.ArrayList;

public class TroubleshootAdapter extends RecyclerView.Adapter<TroubleshootAdapter.TroubleshootHolder> {

    public Context context;
    public ArrayList<Troubleshoot> troubleshootArrayList;

    public TroubleshootAdapter(Context context, ArrayList<Troubleshoot> troubleshootArrayList) {
        this.context = context;
        this.troubleshootArrayList = troubleshootArrayList;
    }

    @NonNull
    @Override
    public TroubleshootAdapter.TroubleshootHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_troubleshoot, parent, false);
        return new TroubleshootHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TroubleshootAdapter.TroubleshootHolder holder, int position) {

        Troubleshoot troubleshoot = troubleshootArrayList.get(position);

        holder.subtitle.setText(troubleshoot.getTitle());
        holder.title.setText(troubleshoot.getTitle());

        holder.cardView.setOnClickListener(v -> {
            Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return troubleshootArrayList.size();
    }

    public class TroubleshootHolder extends RecyclerView.ViewHolder{

        public MaterialCardView cardView;
        public TextView title;
        public TextView subtitle;

        public TroubleshootHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.item_troubleshoot_cardview);
            title = itemView.findViewById(R.id.item_troubleshoot_title);
            subtitle = itemView.findViewById(R.id.item_troubleshoot_subtitle);

        }


    }
}
