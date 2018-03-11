package com.example.mp.clincdatabase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by waboy on 3/11/2018.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {


    private ArrayList<Schedule> scheduleList;
    private Context context;
    private String user;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private TextView schedDate;
        private TextView schedPrescription;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTexts(String date, ArrayList<String> prescription){
            schedDate = (TextView) mView.findViewById(R.id.dateSchedule);
            schedPrescription = (TextView) mView.findViewById(R.id.datePrescription);

            schedDate.setText(date);
            schedPrescription.setText("");

            for(int i = 0; i < prescription.size(); i++){
                schedPrescription.append((i+1) + ") "+prescription.get(i).toString() + "\n");
            }

            int pos = getAdapterPosition();
            if(pos != RecyclerView.NO_POSITION){
                Schedule clickedDataItem = scheduleList.get(pos);

            }
        }
    }

    public ScheduleAdapter(ArrayList<Schedule> scheduleList, String user, Context context){
        this.scheduleList = scheduleList;
        this.user = user;
        this.context = context;
    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_listrow, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {
        holder.setTexts(scheduleList.get(position).getDate(), scheduleList.get(position).getPrescriptions());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }
}
