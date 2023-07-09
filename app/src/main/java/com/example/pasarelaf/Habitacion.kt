package com.example.pasarelaf

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class Habitacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitacion)

        //Volver
        val btnV: Button = findViewById(R.id.btnVolver)
        btnV.setOnClickListener{
            val intent: Intent = Intent(this, Descripcion::class.java)
            startActivity(intent)
        }
        //Llamar a la Pasarela
        val btnR: Button = findViewById(R.id.btnClaseReserva)
        btnR.setOnClickListener{
            val intent: Intent = Intent(this, Pasarela::class.java)
            startActivity(intent)
        }

        val HB = 1
        val FI = "02/01/2023"
        val FF = "04/01/2023"
        val NR = "34"
        val TipoS = "Superior Matrimonial"

        val txtH =findViewById<TextView>(R.id.txtHotel)
        val txtD =findViewById<TextView>(R.id.txtDireccion)
        val txtT =findViewById<TextView>(R.id.txtTipo)
        val txtNR =findViewById<TextView>(R.id.txtNumeroReserva)
        val txtFI =findViewById<TextView>(R.id.txtFechaI)
        val txtFF =findViewById<TextView>(R.id.txtFechaF)
        val txtP =findViewById<TextView>(R.id.txtPrecio)

        val queue = Volley.newRequestQueue(this)
//        val urlReserva = "http://192.168.1.6:8080/api/reservar/v1/reserva"
//        val urlHotel = "http://192.168.1.6:8080/api/reservar/v1/hotel"
//        val urlHabitacion = "http://192.168.1.6:8080/api/reservar/v1/habitacion"
        val urlReserva = "http://20.121.72.196:3000/api/reservar/v1/reserva"
        val urlHotel = "http://20.121.72.196:3000/api/reservar/v1/hotel"
        val urlHabitacion = "http://20.121.72.196:3000/api/reservar/v1/habitacion"

        val stringRequest = StringRequest(com.android.volley.Request.Method.GET,urlHabitacion, Response.Listener { response ->
            val jsonObject = JSONObject(response)

            //Hotel
            val Habitaciones = jsonObject.getJSONArray("datos")
            for(i in 0 until Habitaciones.length()){
                val HabitacionBuscada = Habitaciones.getJSONObject(i)
                if(HabitacionBuscada.getInt("habitacionId") == HB){
                    val precio = HabitacionBuscada.getString("precio")
                    val hotelID = HabitacionBuscada.getString("hotelId")
                    val tipo = TipoS
                    txtT.text = tipo.toString()
                    txtP.text = "S/."+precio.toString()
                    //
                    val stringRequest2 = StringRequest(com.android.volley.Request.Method.GET,urlHotel, Response.Listener { response ->
                        val jsonObject2 = JSONObject(response)

                        //Hotel
                        val Hoteles = jsonObject2.getJSONArray("datos")
                        for(i in 0 until Hoteles.length()){
                            val HotelBuscado = Hoteles.getJSONObject(i)
                            if(HotelBuscado.getInt("hotelId") == hotelID.toInt()){
                                val direccion = HotelBuscado.getString("direccion")
                                val distrito = HotelBuscado.getString("distrito")
                                val ciudad = HotelBuscado.getString("ciudad")
                                txtH.text = "Casa Andina - "+ciudad.toString()
                                txtD.text = distrito+" - "+direccion

                                //Otros valores
                                txtNR.text = "00"+NR
                                txtFI.text = FI
                                txtFF.text = FF
                                break;
                            }
                        }

                    },Response.ErrorListener { error ->
                        Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show();
                    })
                    queue.add(stringRequest2)
                    //
                    break;
                }
            }

        },Response.ErrorListener { error ->
            Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show();
        })
        queue.add(stringRequest)
    }
}
