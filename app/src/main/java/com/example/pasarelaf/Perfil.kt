package com.example.pasarelaf

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Perfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val dato1 = findViewById<TextView>(R.id.dato1)
        val dato2 = findViewById<TextView>(R.id.dato2)
        val dato3 = findViewById<TextView>(R.id.dato3)
        val dato4 = findViewById<TextView>(R.id.dato4)

        val queue = Volley.newRequestQueue(this)
//        val url = "http://192.168.1.6:8080/api/reservar/v1/usuario"
        val url = "http://20.121.72.196:3000/api/reservar/v1/usuario"

        val stringRequest = StringRequest(Request.Method.GET,url, Response.Listener { response ->
            val jsonObject = JSONObject(response)

            val algox = jsonObject.getJSONArray("datos")
            for(i in 0 until algox.length()){
                val algo2 = algox.getJSONObject(i)
                val algo3 = algo2.getString("nombres")
                val algo4 = algo2.getString("apellidos")
                val algo5 = algo2.getString("correo")
                val algo6 = algo2.getString("telefono")
                when(algo2.getInt("usuarioId")){
                    1 ->{
                        dato1.text = algo3.toString()
                        dato2.text = algo4.toString()
                        dato3.text = algo5.toString()
                        dato4.text = algo6.toString()
                    }
                }
            }

        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show();
        })
        queue.add(stringRequest)
    }

    fun regresarciudad(view: View){
        finish()
    }
}

