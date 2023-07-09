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

class Habitaciones3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitaciones3)

        val TamH = "30m2"
        val numhabit1 = findViewById<TextView>(R.id.numhabit1)
        val cd1 = findViewById<TextView>(R.id.cd1)
        val cp1 = findViewById<TextView>(R.id.cp1)
        val tamaño1 = findViewById<TextView>(R.id.tamaño1)
        val precio1 = findViewById<TextView>(R.id.precio1)


        val numhabit2 = findViewById<TextView>(R.id.numhabit2)
        val cd2 = findViewById<TextView>(R.id.cd2)
        val cp2 = findViewById<TextView>(R.id.cp2)
        val tamaño2 = findViewById<TextView>(R.id.tamaño2)
        val precio2 = findViewById<TextView>(R.id.precio2)

        val numhabit3 = findViewById<TextView>(R.id.numhabit3)
        val cd3 = findViewById<TextView>(R.id.cd3)
        val cp3 = findViewById<TextView>(R.id.cp3)
        val tamaño3 = findViewById<TextView>(R.id.tamaño3)
        val precio3 = findViewById<TextView>(R.id.precio3)

        val numhabit4 = findViewById<TextView>(R.id.numhabit4)
        val cd4 = findViewById<TextView>(R.id.cd4)
        val cp4 = findViewById<TextView>(R.id.cp4)
        val tamaño4 = findViewById<TextView>(R.id.tamaño4)
        val precio4 = findViewById<TextView>(R.id.precio4)

        val queue = Volley.newRequestQueue(this)
//        val url = "http://192.168.1.6:8080/api/reservar/v1/habitacion"
        val url = "http://20.121.72.196:3000/api/reservar/v1/habitacion"

        val stringRequest = StringRequest(Request.Method.GET,url, Response.Listener { response ->
            val jsonObject = JSONObject(response)

            val algoy = jsonObject.getJSONArray("datos")
            for(i in 0 until algoy.length()){
                val algo2 = algoy.getJSONObject(i)

                val algo3 = algo2.getInt("habitacionId")
                val algo4 = algo2.getString("camasDoble")
                val algo5 = algo2.getString("camasPersonales")
                val algo6 = TamH
                val algo7 = algo2.getString("precio")

                when(algo2.getInt("habitacionId") ){
                    9-> {
                        numhabit1.text = algo3.toString()
                        cd1.text = algo4.toString()
                        cp1.text = algo5.toString()
                        tamaño1.text = algo6.toString()
                        precio1.text = "S/ " +algo7.toString()
                    }
                    10->{
                        numhabit2.text = algo3.toString()
                        cd2.text = algo4.toString()
                        cp2.text = algo5.toString()
                        tamaño2.text = "35m2"
                        precio2.text = "S/ " +algo7.toString()
                    }
                    11->{
                        numhabit3.text = algo3.toString()
                        cd3.text = algo4.toString()
                        cp3.text = algo5.toString()
                        tamaño3.text = "35m2"
                        precio3.text = "S/ " +algo7.toString()
                    }
                    12->{
                        numhabit4.text = algo3.toString()
                        cd4.text = algo4.toString()
                        cp4.text = algo5.toString()
                        tamaño4.text = "35m2"
                        precio4.text = "S/ " +algo7.toString()
                    }
                }
            }

        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show();
        })
        queue.add(stringRequest)

    }


    fun regresar (view: View){
//        finish()
        val reg = Intent(this,Ciudades::class.java)
        startActivity(reg)
    }

    fun usuariohabit3(view: View){
        val ushabit3 = Intent(this,Perfil::class.java)
        startActivity(ushabit3)
    }
    fun reserva1(view: View){
        val res1 = Intent(this,Reservas::class.java)
        startActivity(res1)
    }
    fun descripcion2(view: View){
        val desc2 = Intent(this,Descripcion2::class.java)
        startActivity(desc2)
    }
}