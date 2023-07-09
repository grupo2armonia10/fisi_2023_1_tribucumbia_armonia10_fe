package com.example.pasarelaf

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun cancelarSignUp(view: View){
        finish()
    }
}

