package com.example.pasarelaf

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Descripcion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion)

        //Volver
        val btnV: Button = findViewById(R.id.btnClaseReserva1)
        btnV.setOnClickListener{
            val intent: Intent = Intent(this, Habitacion::class.java)
            startActivity(intent)
        }
        //Llamar a la Pasarela
        val btnR: Button = findViewById(R.id.btnVolver10)
        btnR.setOnClickListener{
            val intent: Intent = Intent(this, Habitaciones1::class.java)
            startActivity(intent)
        }

        val NH = 1
        val Tam = "30m2"
        val TipoS = "Superior Matrimonial"
        val txtV1 =findViewById<TextView>(R.id.txtTipo)
        val txtV2 =findViewById<TextView>(R.id.txtCama1)
        val txtV3 =findViewById<TextView>(R.id.txtCama2)
        val txtV4 =findViewById<TextView>(R.id.txtTam)
        val txtV5 =findViewById<TextView>(R.id.txtPrecio)

        val queue = Volley.newRequestQueue(this)
//        val urlReserva = "http://192.168.1.6:8080/api/reservar/v1/reserva"
//        val urlHotel = "http://192.168.1.6:8080/api/reservar/v1/hotel"
//        val urlHabitacion = "http://192.168.1.6:8080/api/reservar/v1/habitacion"
        val urlReserva = "http://20.121.72.196:3000/api/reservar/v1/reserva"
        val urlHotel = "http://20.121.72.196:3000/api/reservar/v1/hotel"
        val urlHabitacion = "http://20.121.72.196:3000/api/reservar/v1/habitacion"

        val stringRequest = StringRequest(Request.Method.GET,urlHabitacion, Response.Listener { response ->
            val jsonObject = JSONObject(response)

            //Habitacion
            val Habitaciones = jsonObject.getJSONArray("datos")
            for(i in 0 until Habitaciones.length()){
                val HabitacionBuscada = Habitaciones.getJSONObject(i)
                if(HabitacionBuscada.getInt("habitacionId") == NH){
                    val tipo = TipoS
                    val cama1 = HabitacionBuscada.getString("camasPersonales")
                    val cama2 = HabitacionBuscada.getString("camasDoble")
                    val tam = Tam
                    val precio = HabitacionBuscada.getString("precio")
                    txtV1.text = tipo.toString()
                    txtV2.text = cama1.toString()
                    txtV3.text = cama2.toString()
                    txtV4.text = tam.toString()
                    txtV5.text = "S/."+precio.toString()
                }
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show();
        })
        queue.add(stringRequest)
    }
}