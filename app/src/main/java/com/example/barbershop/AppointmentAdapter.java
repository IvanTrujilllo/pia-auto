package com.example.barbershop;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershop.models.Appointment;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.MyViewHolder> {
    private List<Appointment> appointmentsList;

    public void setAppointmentsList(List<Appointment> appointmentsList){
        this.appointmentsList = appointmentsList;
    }

    public AppointmentAdapter(List<Appointment> appointments){
        this.appointmentsList = appointments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View appointmentRow = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.appointment_row, viewGroup, false);

        return new MyViewHolder(appointmentRow);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i){
        Appointment appointment = appointmentsList.get(i);

        String appointmentName = appointment.getName() + " " + appointment.getLastname();
        int appointmentAge = appointment.getAge();
        String date = appointment.getDate();

        myViewHolder.name.setText(appointmentName);
        myViewHolder.age.setText(String.valueOf(appointmentAge));
        myViewHolder.date.setText(date);
    }

    @Override
    public int getItemCount() {
        return appointmentsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, date;

        MyViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.tvName);
            this.age = itemView.findViewById(R.id.tvAge);
            this.date = itemView.findViewById(R.id.tvDate);
        }
    }
}
