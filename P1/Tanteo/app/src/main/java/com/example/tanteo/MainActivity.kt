package com.example.tanteo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tanteo.databinding.ActivityMainBinding

const val LOCAL1 = "0"
const val LOCAL2 = "0"
const val LOCAL3 = "0"
const val LOCALTOTAL = "0"

const val VISIT1 = "0"
const val VISIT2 = "0"
const val VISIT3 = "0"
const val VISITTOTAL = "0"

class MainActivity : AppCompatActivity() {

    private var local1 = 0; private var local2 = 0; private var local3 = 0
    private var visit1 = 0; private var visit2 = 0; private var visit3 = 0

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val local = TeamPoints(0,0,0)
    val visit = TeamPoints(0,0,0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configurarBoton(R.id.btnLocal1, R.id.tvCountLocal1, 1, true)
        configurarBoton(R.id.btnLocal2, R.id.tvCountLocal2, 2, true)
        configurarBoton(R.id.btnLocal3, R.id.tvCountLocal3, 3, true)

        configurarBoton(R.id.btnVisit1, R.id.tvCountVisit1, 1, false)
        configurarBoton(R.id.btnVisit2, R.id.tvCountVisit2, 2, false)
        configurarBoton(R.id.btnVisit3, R.id.tvCountVisit3, 3, false)

        val btnEstadisticas = binding.btnEstadisticas
        btnEstadisticas.setOnClickListener {
            irAEstadisticas()
        }
    }

    override fun onSaveInstanceState(outState : Bundle){
        super.onSaveInstanceState(outState)
        // Añadir WITH
        outState.putString(LOCAL1, binding.tvCountLocal1.text.toString())
        outState.putString(LOCAL2, binding.tvCountLocal2.text.toString())
        outState.putString(LOCAL3, binding.tvCountLocal3.text.toString())
        outState.putString(LOCALTOTAL, binding.tvPuntosLocal.text.toString())

        outState.putString(VISIT1, binding.tvCountVisit1.text.toString())
        outState.putString(VISIT2, binding.tvCountVisit2.text.toString())
        outState.putString(VISIT3, binding.tvCountVisit3.text.toString())
        outState.putString(VISITTOTAL, binding.tvPuntosVisitante.text.toString())

        outState.putParcelable("key", local)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle){
        super.onRestoreInstanceState(savedInstanceState)
        binding.tvCountLocal1.text = savedInstanceState.getString(LOCAL1)
        binding.tvCountLocal2.text = savedInstanceState.getString(LOCAL2)
        binding.tvCountLocal3.text = savedInstanceState.getString(LOCAL3)
        binding.tvPuntosLocal.text = savedInstanceState.getString(LOCALTOTAL)

        binding.tvCountVisit1.text = savedInstanceState.getString(VISIT1)
        binding.tvCountVisit2.text = savedInstanceState.getString(VISIT2)
        binding.tvCountVisit3.text = savedInstanceState.getString(VISIT3)
        binding.tvPuntosVisitante.text = savedInstanceState.getString(VISITTOTAL)
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

        binding.tvPuntosLocal.text = sumaLocal.toString()
        binding.tvPuntosVisitante.text = sumaVisitante.toString()
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