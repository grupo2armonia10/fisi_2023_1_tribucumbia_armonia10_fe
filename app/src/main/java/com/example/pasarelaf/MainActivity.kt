package com.example.pasarelaf

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_inicio)
/*
        //Llamar a la clase Habitacion
        val btnH: Button = findViewById(R.id.btnHabitacion)
        btnH.setOnClickListener{
            val intent: Intent = Intent(this, Descripcion::class.java)
            startActivity(intent)
        }

        //Llamar a la clase mis reservas
        val btnR: Button = findViewById(R.id.btnMisReservas)
        btnR.setOnClickListener{
            val intent: Intent = Intent(this, Reservas::class.java)
            startActivity(intent)
        }
 */
    }

    fun iniciologin(view: View) {
        val logi = Intent(this, Login::class.java)
        startActivity(logi)
    }

    fun inicioregis(view: View) {
        val regis = Intent(this, SignUp::class.java)
        startActivity(regis)
    }

}