package com.example.tanteo

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EstadisticasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)

        val l1 = intent.getIntExtra("L1", 0)
        val l2 = intent.getIntExtra("L2", 0)
        val l3 = intent.getIntExtra("L3", 0)
        val v1 = intent.getIntExtra("V1", 0)
        val v2 = intent.getIntExtra("V2", 0)
        val v3 = intent.getIntExtra("V3", 0)

        actualizarFila(R.id.pbLocal1, R.id.tvStatLocal1, l1)
        actualizarFila(R.id.pbLocal2, R.id.tvStatLocal2, l2)
        actualizarFila(R.id.pbLocal3, R.id.tvStatLocal3, l3)

        actualizarFila(R.id.pbVisit1, R.id.tvStatVisit1, v1)
        actualizarFila(R.id.pbVisit2, R.id.tvStatVisit2, v2)
        actualizarFila(R.id.pbVisit3, R.id.tvStatVisit3, v3)

        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
        }
    }

    private fun actualizarFila(pbId: Int, tvId: Int, valor: Int) {
        findViewById<ProgressBar>(pbId).progress = valor
        findViewById<TextView>(tvId).text = valor.toString()
    }
}