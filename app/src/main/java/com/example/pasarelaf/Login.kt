package com.example.pasarelaf

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun entrar(view: View){
        val logear = Intent(this, Ciudades::class.java)
        startActivity(logear)
    }

    fun cancelarLogin(view: View){
        finish()
    }
}