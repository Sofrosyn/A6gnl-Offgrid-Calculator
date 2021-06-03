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

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder> {

    public Context context;
    public ArrayList<Notification> notificationArrayList;

    public NotificationAdapter(Context context, ArrayList<Notification> notificationArrayList) {
        this.context = context;
        this.notificationArrayList = notificationArrayList;
    }

    @NonNull
    @Override
    public NotificationAdapter.NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_notification, parent, false);
        return new NotificationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationHolder holder, int position) {

        Notification notification = notificationArrayList.get(position);

        holder.subtitle.setText(notification.getSubtitle());
        holder.title.setText(notification.getTitle());

        holder.cardView.setOnClickListener(v -> {
            Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    public class NotificationHolder extends RecyclerView.ViewHolder{

        public MaterialCardView cardView;
        public TextView title;
        public TextView subtitle;

        public NotificationHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.notification_item_card);
            title = itemView.findViewById(R.id.notification_item_title);
            subtitle = itemView.findViewById(R.id.notification_item_subtitle);

        }


    }
}
