package com.example.barbershop.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.barbershop.DataBaseHelper;
import com.example.barbershop.models.Appointment;

import java.util.ArrayList;

public class AppointmentsController {
    private DataBaseHelper dataBaseHelper;
    private String TABLE_NAME = "appointments";

    public AppointmentsController(Context context) {
        this.dataBaseHelper = new DataBaseHelper(context, this.TABLE_NAME);
    }

    public long newAppointment(Appointment appointment) {
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", appointment.getName());
        contentValues.put("lastname", appointment.getLastname());
        contentValues.put("age", appointment.getAge());
        contentValues.put("date", appointment.getDate());
        return database.insert(TABLE_NAME, null, contentValues);
    }

    public int updateAppointment(Appointment editedAppointment){
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        ContentValues updateValues = new ContentValues();
        updateValues.put("name", editedAppointment.getName());
        updateValues.put("lastname", editedAppointment.getLastname());
        updateValues.put("age", editedAppointment.getAge());
        updateValues.put("date", editedAppointment.getDate());
        // where id...
        String updateColumn = "id = ?";
        // = idAppointment
        String[] updateArguments = {String.valueOf(editedAppointment.getId())};
        return database.update(TABLE_NAME, updateValues, updateColumn, updateArguments);
    }

    public int deleteAppointment(Appointment appointment){
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        String[] deleteArguments = {String.valueOf(appointment.getId())};
        return database.delete(TABLE_NAME, "id = ?", deleteArguments);
    }

    public ArrayList<Appointment> getAppointments(){
        ArrayList<Appointment> appointments = new ArrayList<>();
        SQLiteDatabase database = dataBaseHelper.getReadableDatabase();
        String[] consultedColumns = {"name", "lastname", "age", "date", "id"};

        Cursor cursor = database.query(
                TABLE_NAME,
                consultedColumns,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null){
            return appointments;
        }

        if (!cursor.moveToFirst()) return appointments;

        do {
            String nameFromDatabase = cursor.getString(0);
            String lastnameFromDatabase = cursor.getString(1);
            int ageFromDatabase = cursor.getInt(2);
            String dateFromDatabase = cursor.getString(3);
            long appointmentId = cursor.getLong(4);

            appointments.add(new Appointment(nameFromDatabase, lastnameFromDatabase,
                    ageFromDatabase, dateFromDatabase, appointmentId));
        } while (cursor.moveToNext());

        cursor.close();
        return appointments;
    }
}
