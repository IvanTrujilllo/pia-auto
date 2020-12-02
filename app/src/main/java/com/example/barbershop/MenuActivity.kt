package com.example.barbershop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun cancelarCita(view: View) {
        startActivity(Intent( this, AppointmentsIndexActivity::class.java))
    }

    fun crearCita(view: View){
        startActivity(Intent(this, AddAppointmentActivity::class.java))
    }

    fun servicios(view: View){
        startActivity(Intent(this, ServiciosActivity::class.java))
    }
}