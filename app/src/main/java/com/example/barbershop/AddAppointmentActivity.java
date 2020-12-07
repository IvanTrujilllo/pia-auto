package com.example.barbershop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barbershop.controllers.AppointmentsController;
import com.example.barbershop.models.Appointment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddAppointmentActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText etName, etAge, etLastname, etService;
    private Button btnAddAppointment, btnCancelNewAppointment;
    private AppointmentsController appointmentsController;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        // instance views
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etLastname = (EditText) findViewById(R.id.etLastname);
        etService = (EditText) findViewById(R.id.etService);
        datePicker = (DatePicker) findViewById(R.id.cvDate);
        timePicker = (TimePicker) findViewById(R.id.tpTime);
        btnAddAppointment = (Button) findViewById(R.id.btnAddAppointment);
        btnCancelNewAppointment = (Button) findViewById(R.id.btnCancelNewAppointment);

        // create controller
        appointmentsController = new AppointmentsController(AddAppointmentActivity.this);

        // add listener of save button
        btnAddAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // reset error on both fields
                etName.setError(null);
                etLastname.setError(null);
                etAge.setError(null);
                etService.setError(null);
                StringBuilder dateBuilder = new StringBuilder();
                String name = etName.getText().toString(),
                        lastname = etLastname.getText().toString(),
                        ageAsString = etAge.getText().toString(),
                        service = etService.getText().toString(),
                        dateAsString;

                dateBuilder.append(String.valueOf(datePicker.getYear()) + "-");
                dateBuilder.append(String.valueOf(datePicker.getMonth()) + "-");
                dateBuilder.append(String.valueOf(datePicker.getDayOfMonth()) + " ");
                dateBuilder.append(String.format("%02d:%02d",
                        timePicker.getCurrentHour(), timePicker.getCurrentMinute()));

                dateAsString = dateBuilder.toString();

                if ("".equals(name)){
                    etName.setError("Escribe el nombre de la mascota");
                    etName.requestFocus();
                    return;
                }
                if ("".equals(ageAsString)) {
                    etAge.setError("Escribe la edad de la mascota");
                    etAge.requestFocus();
                    return;
                }

                // add age integer validation
                // Ver si es un entero
                int age;
                try {
                    age = Integer.parseInt(etAge.getText().toString());
                } catch (NumberFormatException e) {
                    etAge.setError("Escribe un número");
                    etAge.requestFocus();
                    return;
                }

                // after all validations passed
                Appointment newAppointment = new Appointment(name, lastname, age, dateAsString);
                long id = appointmentsController.newAppointment(newAppointment);
                if (id == -1) {
                    // an error occurred
                    Toast.makeText(AddAppointmentActivity.this,
                            "Error al guardar. Intenta de nuevo",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    finish();
                }

            }
        });

        // cancel button just finishes activity
        btnCancelNewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
