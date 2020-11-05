package com.example.barbershop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrarActivity : AppCompatActivity() {

    private lateinit var txtNombre:EditText
    private lateinit var txtApellido:EditText
    private lateinit var txtEdad:EditText
    private lateinit var txtCorreo:EditText
    private lateinit var txtPassword:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtEdad = findViewById(R.id.txtEdad)
        txtCorreo = findViewById(R.id.txtCorreo)
        txtPassword = findViewById(R.id.txtPassword)
        progressBar = findViewById(R.id.progressBar)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbReference = database.reference.child("User")
    }

    fun registrar(view:View){
        createNewAccount()
    }

    private fun createNewAccount(){
        val nombre:String = txtNombre.text.toString()
        val apellido:String = txtApellido.text.toString()
        val edad:String = txtEdad.text.toString()
        val correo:String = txtCorreo.text.toString()
        val contraseña:String = txtPassword.text.toString()

        if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(apellido) && !TextUtils.isEmpty(edad) && !TextUtils.isEmpty(correo) &&
            !TextUtils.isEmpty(contraseña)){
            progressBar.visibility = View.VISIBLE

            auth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(this){
                    task ->
                    if (task.isComplete){
                        val user:FirebaseUser? = auth.currentUser
                        verifyEmail(user)

                        val userBD = dbReference.child(user?.uid!!)

                        userBD.child("Nombre").setValue(nombre)
                        userBD.child("Apellido").setValue(apellido)
                        action()
                    }
                }

        }
    }

    private fun action(){
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun verifyEmail(user:FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                task ->
                if (task.isComplete){
                    Toast.makeText(this, "Correo enviado", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Error al enviar el correo", Toast.LENGTH_LONG).show()
                }
            }
    }
}