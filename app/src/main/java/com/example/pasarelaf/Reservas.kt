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

class Reservas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        //Volver
//        val btnV: Button = findViewById(R.id.btnVolver10)
//        btnV.setOnClickListener{
//            val intent: Intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }

        val HB = 1
        val HB2 = 2
        val FI = "02/01/2023"
        val FF = "04/01/2023"
        val FI2 = "07/01/2023"
        val FF2 = "10/01/2023"
        val NR = "34"
        val TipoS = "Superior Matrimonial"

        val txtD =findViewById<TextView>(R.id.direccion1)
        val txtT =findViewById<TextView>(R.id.tiphabit1)
        val txtF1 =findViewById<TextView>(R.id.entrada1)
        val txtF2 =findViewById<TextView>(R.id.salida1)
        val txtP =findViewById<TextView>(R.id.precio1)

        val txtD2 =findViewById<TextView>(R.id.direccion2)
        val txtT2 =findViewById<TextView>(R.id.tiphabit2)
        val txtF12 =findViewById<TextView>(R.id.entrada2)
        val txtF22 =findViewById<TextView>(R.id.salida2)
        val txtP2 =findViewById<TextView>(R.id.precio2)

        val queue = Volley.newRequestQueue(this)
        //        val urlReserva = "http://192.168.1.6:8080/api/reservar/v1/reserva"
//        val urlHotel = "http://192.168.1.6:8080/api/reservar/v1/hotel"
//        val urlHabitacion = "http://192.168.1.6:8080/api/reservar/v1/habitacion"
        val urlReserva = "http://20.121.72.196:3000/api/reservar/v1/reserva"
        val urlHotel = "http://20.121.72.196:3000/api/reservar/v1/hotel"
        val urlHabitacion = "http://20.121.72.196:3000/api/reservar/v1/habitacion"

        val stringRequest = StringRequest(Request.Method.GET,urlHabitacion, Response.Listener { response ->
            val jsonObject = JSONObject(response)

            //Habitaciones
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
                    val stringRequest2 = StringRequest(Request.Method.GET,urlHotel, Response.Listener { response ->
                        val jsonObject2 = JSONObject(response)

                        //Hotel
                        val Hoteles = jsonObject2.getJSONArray("datos")
                        for(i in 0 until Hoteles.length()){
                            val HotelBuscado = Hoteles.getJSONObject(i)
                            if(HotelBuscado.getInt("hotelId") == hotelID.toInt()){
                                val direccion = HotelBuscado.getString("direccion")
                                val distrito = HotelBuscado.getString("distrito")
                                val ciudad = HotelBuscado.getString("ciudad")
                                txtD.text = ciudad+" - "+distrito+" - "+direccion
                                //txtD.text = distrito+" - "+direccion

                                //Otros valores
                                txtF1.text = FI
                                txtF2.text = FF
                                break;
                            }
                        }

                    }, Response.ErrorListener { error ->
                        Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show();
                    })
                    queue.add(stringRequest2)
                }

                //
                if(HabitacionBuscada.getInt("habitacionId") == HB2){
                    val precio2 = HabitacionBuscada.getString("precio")
                    val hotelID2 = HabitacionBuscada.getString("hotelId")
                    val tipo2 = TipoS
                    txtT2.text = tipo2.toString()
                    txtP2.text = "S/."+precio2.toString()
                    //
                    val stringRequest2 = StringRequest(Request.Method.GET,urlHotel, Response.Listener { response ->
                        val jsonObject2 = JSONObject(response)

                        //Hotel
                        val Hoteles = jsonObject2.getJSONArray("datos")
                        for(i in 0 until Hoteles.length()){
                            val HotelBuscado = Hoteles.getJSONObject(i)
                            if(HotelBuscado.getInt("hotelId") == hotelID2.toInt()){
                                val direccion = HotelBuscado.getString("direccion")
                                val distrito = HotelBuscado.getString("distrito")
                                val ciudad = HotelBuscado.getString("ciudad")
                                txtD2.text = ciudad+" - "+distrito+" - "+direccion
                                //txtD.text = distrito+" - "+direccion

                                //Otros valores
                                txtF12.text = FI2
                                txtF22.text = FF2
                                break;
                            }
                        }

                    }, Response.ErrorListener { error ->
                        Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show();
                    })
                    queue.add(stringRequest2)
                    //
                    break;
                }
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext, "Error de conexión", Toast.LENGTH_SHORT).show();
        })
        queue.add(stringRequest)
    }
    fun volverCiudades(view: View){
        finish()
    }
}