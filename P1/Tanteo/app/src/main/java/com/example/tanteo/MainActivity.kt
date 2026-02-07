package com.example.tanteo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var local1 = 0; private var local2 = 0; private var local3 = 0
    private var visit1 = 0; private var visit2 = 0; private var visit3 = 0

    private lateinit var tvPuntosLocal: TextView
    private lateinit var tvPuntosVisitante: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPuntosLocal = findViewById(R.id.tvPuntosLocal)
        tvPuntosVisitante = findViewById(R.id.tvPuntosVisitante)

        configurarBoton(R.id.btnLocal1, R.id.tvCountLocal1, 1, true)
        configurarBoton(R.id.btnLocal2, R.id.tvCountLocal2, 2, true)
        configurarBoton(R.id.btnLocal3, R.id.tvCountLocal3, 3, true)

        configurarBoton(R.id.btnVisit1, R.id.tvCountVisit1, 1, false)
        configurarBoton(R.id.btnVisit2, R.id.tvCountVisit2, 2, false)
        configurarBoton(R.id.btnVisit3, R.id.tvCountVisit3, 3, false)

        val btnEstadisticas = findViewById<Button>(R.id.btnEstadisticas)
        btnEstadisticas.setOnClickListener {
            irAEstadisticas()
        }
    }

    private fun configurarBoton(idBoton: Int, idTexto: Int, puntos: Int, esLocal: Boolean) {
        val boton = findViewById<Button>(idBoton)
        val textoContador = findViewById<TextView>(idTexto)

        boton.setOnClickListener {
            if (esLocal) {
                when (puntos) {
                    1 -> local1++
                    2 -> local2++
                    3 -> local3++
                }
                textoContador.text = when(puntos) {
                    1 -> local1.toString()
                    2 -> local2.toString()
                    else -> local3.toString()
                }
            } else {
                when (puntos) {
                    1 -> visit1++
                    2 -> visit2++
                    3 -> visit3++
                }
                textoContador.text = when(puntos) {
                    1 -> visit1.toString()
                    2 -> visit2.toString()
                    else -> visit3.toString()
                }
            }
            actualizarTotales()
        }
    }

    private fun actualizarTotales() {
        val sumaLocal = (local1 * 1) + (local2 * 2) + (local3 * 3)
        val sumaVisitante = (visit1 * 1) + (visit2 * 2) + (visit3 * 3)

        tvPuntosLocal.text = sumaLocal.toString()
        tvPuntosVisitante.text = sumaVisitante.toString()
    }

    private fun irAEstadisticas() {
        val intent = Intent(this, EstadisticasActivity::class.java)
        // Pasamos los valores actuales
        intent.putExtra("L1", local1)
        intent.putExtra("L2", local2)
        intent.putExtra("L3", local3)
        intent.putExtra("V1", visit1)
        intent.putExtra("V2", visit2)
        intent.putExtra("V3", visit3)
        startActivity(intent)
    }
}