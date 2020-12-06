package com.example.barbershop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershop.controllers.AppointmentsController;
import com.example.barbershop.models.Appointment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsIndexActivity extends AppCompatActivity {
    private List<Appointment> appointmentsList;
    private RecyclerView recyclerView;
    private AppointmentAdapter appointmentAdapter;
    private AppointmentsController appointmentsController;
    private FloatingActionButton fabAddAppointment;
    private Button btnExit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments_index);

        appointmentsController = new AppointmentsController(AppointmentsIndexActivity.this);
        recyclerView = findViewById(R.id.recyclerViewPets);
        fabAddAppointment = findViewById(R.id.fabAddPet);
        // btnExit = findViewById(R.id.btnAppointmentExit);

        appointmentsList = new ArrayList<>();
        appointmentAdapter = new AppointmentAdapter(appointmentsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(appointmentAdapter);

        refreshAppointmentList();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                /* // go to EditAppointmentActivity.java
                Appointment appointmentSelected = appointmentsList.get(position);
                Intent intent = new Intent(AppointmentsIndexActivity.this, EditAppointmentActivity.class);
                intent.putExtra("appointmentId", appointmentSelected.getId());
                intent.putExtra("appointmentName", appointmentSelected.getName());
                intent.putExtra("appointmentAge", appointmentSelected.getAge());
                startActivity(intent); */
            }

            @Override
            public void onLongClick(View view, int position) {
                final Appointment appointmentToDelete = appointmentsList.get(position);
                AlertDialog alertDialog = new AlertDialog
                        .Builder(AppointmentsIndexActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                appointmentsController.deleteAppointment(appointmentToDelete);
                                refreshAppointmentList();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar la cita de " + appointmentToDelete.getName() + "?")
                        .create();
                alertDialog.show();
            }
        }));

        // float button listener
        fabAddAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // it just changes activity
                Intent intent = new Intent(AppointmentsIndexActivity.this, AgendarActivity.class);
                startActivity(intent);
            }
        });

    }

    public void refreshAppointmentList(){
        if (appointmentAdapter == null) return;
        appointmentsList = appointmentsController.getAppointments();
        appointmentAdapter.setAppointmentsList(appointmentsList);
        appointmentAdapter.notifyDataSetChanged();
    }
}
