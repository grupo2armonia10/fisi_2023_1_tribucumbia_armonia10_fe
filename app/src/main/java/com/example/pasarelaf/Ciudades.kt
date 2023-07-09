package com.example.pasarelaf

import android.content.Intent
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

class Ciudades : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val tvResultado = findViewById<TextView>(R.id.tvResultado1)
        val tvResultado2 = findViewById<TextView>(R.id.tvResultado2)
        val tvResultado3 = findViewById<TextView>(R.id.tvResultado3)
        val tvResultado4 = findViewById<TextView>(R.id.tvResultado4)
        val tvResultado5 = findViewById<TextView>(R.id.tvResultado5)

        val tef1 = findViewById<TextView>(R.id.tef1)
        val tef2 = findViewById<TextView>(R.id.tef2)
        val tef3 = findViewById<TextView>(R.id.tef3)
        val tef4 = findViewById<TextView>(R.id.tef4)
        val tef5 = findViewById<TextView>(R.id.tef5)

        val queue = Volley.newRequestQueue(this)
//        val url = "http://192.168.1.6:8080/api/reservar/v1/hotel"
        val url = "http://20.121.72.196:3000/api/reservar/v1/hotel"

        val stringRequest = StringRequest(Request.Method.GET,url, Response.Listener { response ->
            val jsonObject = JSONObject(response)

            val algox = jsonObject.getJSONArray("datos")
            for(i in 0 until algox.length()){
                val algo2 = algox.getJSONObject(i)
                val algo3 = algo2.getString("direccion")
                val algo4 = algo2.getString("distrito")
                val algo5 = algo2.getString("ciudad")
                val algo6 = "+51 5240133"
                when(algo2.getInt("hotelId")){
                    1 ->{
                        tvResultado.text = algo3.toString()+" - "+algo4.toString()+", "+algo5.toString()+"."
                        tef1.text = algo6.toString()
                    }
                    2 ->{
                        tvResultado2.text = algo3.toString()+" - "+algo4.toString()+", "+algo5.toString()+"."
                        tef2.text = algo6.toString()
                    }
                    3 ->{
                        tvResultado3.text = algo3.toString()+" - "+algo4.toString()+", "+algo5.toString()+"."
                        tef3.text = algo6.toString()
                    }
                    4 ->{
                        tvResultado4.text = algo3.toString()+" - "+algo4.toString()+", "+algo5.toString()+"."
                        tef4.text = algo6.toString()
                    }
                    5 ->{
                        tvResultado5.text = algo3.toString()+" - "+algo4.toString()+", "+algo5.toString()+"."
                        tef5.text = algo6.toString()
                    }

                }
            }

        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show();
        })
        queue.add(stringRequest)
    }


    fun visualizar1(view: View){
        val visu = Intent(this,Habitaciones1::class.java)
        startActivity(visu)
    }

    fun visualizar2(view: View){
        val visu = Intent(this,Habitaciones2::class.java)
        startActivity(visu)
    }

    fun visualizar3(view: View){
        val visu = Intent(this,Habitaciones3::class.java)
        startActivity(visu)
    }

    fun visualizar4(view: View){
        val visu = Intent(this,Habitaciones4::class.java)
        startActivity(visu)
    }

    fun visualizar5(view: View){
        val visu = Intent(this,Habitaciones5::class.java)
        startActivity(visu)
    }

    fun usuariociud(view: View){
        val usciudad = Intent(this,Perfil::class.java)
        startActivity(usciudad)
    }
    fun salir(view: View){
        val sal1 = Intent(this,Login::class.java)
        startActivity(sal1)
    }
    fun reserva1(view: View){
        val res1 = Intent(this,Reservas::class.java)
        startActivity(res1)
    }
}