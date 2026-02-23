package com.example.tanteo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tanteo.databinding.ActivityMainBinding

const val LOCAL1 = "local1"
const val LOCAL2 = "local2"
const val LOCAL3 = "local3"
const val LOCALTOTAL = "localTotal"

const val VISIT1 = "visit1"
const val VISIT2 = "visit2"
const val VISIT3 = "visit3"
const val VISITTOTAL = "visitTotal"

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // Usamos TeamPoints para gestionar los puntos de cada equipo
    var local = TeamPoints(0, 0, 0)
    var visit = TeamPoints(0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configurarBoton(R.id.btnLocal1, R.id.tvCountLocal1, 1, true)
        configurarBoton(R.id.btnLocal2, R.id.tvCountLocal2, 2, true)
        configurarBoton(R.id.btnLocal3, R.id.tvCountLocal3, 3, true)

        configurarBoton(R.id.btnVisit1, R.id.tvCountVisit1, 1, false)
        configurarBoton(R.id.btnVisit2, R.id.tvCountVisit2, 2, false)
        configurarBoton(R.id.btnVisit3, R.id.tvCountVisit3, 3, false)

        binding.btnEstadisticas.setOnClickListener {
            irAEstadisticas()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("local", local)
        outState.putParcelable("visit", visit)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        local = savedInstanceState.getParcelable("local") ?: TeamPoints(0, 0, 0)
        visit = savedInstanceState.getParcelable("visit") ?: TeamPoints(0, 0, 0)

        // Refrescamos la UI con los valores restaurados
        binding.tvCountLocal1.text = local.de1.toString()
        binding.tvCountLocal2.text = local.de2.toString()
        binding.tvCountLocal3.text = local.de3.toString()
        binding.tvPuntosLocal.text = local.total.toString()

        binding.tvCountVisit1.text = visit.de1.toString()
        binding.tvCountVisit2.text = visit.de2.toString()
        binding.tvCountVisit3.text = visit.de3.toString()
        binding.tvPuntosVisitante.text = visit.total.toString()
    }

    private fun configurarBoton(idBoton: Int, idTexto: Int, puntos: Int, esLocal: Boolean) {
        val boton = findViewById<Button>(idBoton)
        val textoContador = findViewById<TextView>(idTexto)

        boton.setOnClickListener {
            val equipo = if (esLocal) local else visit

            when (puntos) {
                1 -> equipo.de1++
                2 -> equipo.de2++
                3 -> equipo.de3++
            }

            textoContador.text = when (puntos) {
                1 -> equipo.de1.toString()
                2 -> equipo.de2.toString()
                else -> equipo.de3.toString()
            }

            actualizarTotales()
        }
    }

    private fun actualizarTotales() {
        binding.tvPuntosLocal.text = local.total.toString()
        binding.tvPuntosVisitante.text = visit.total.toString()
    }

    private fun irAEstadisticas() {
        val intent = Intent(this, EstadisticasActivity::class.java)
        intent.putExtra("L1", local.de1)
        intent.putExtra("L2", local.de2)
        intent.putExtra("L3", local.de3)
        intent.putExtra("V1", visit.de1)
        intent.putExtra("V2", visit.de2)
        intent.putExtra("V3", visit.de3)
        startActivity(intent)
    }
}