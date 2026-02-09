package com.example.basicoprimero

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.basicoprimero.databinding.ActivityMainBinding

const val TAG = "Actividad 1"
const val ETIQUETA = "Etiqueta"
class MainActivity : AppCompatActivity() {

    /*
    val boton : Button by lazy { findViewById(R.id.button) }
    val etiqueta : TextView by lazy {
        findViewById(R.id.textView)
    }
    */
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d(TAG, "Estoy en OnCreate")
        if (savedInstanceState==null){
            Log.d(TAG, "Bundle es nulo")
        }
        else{
            Log.d(TAG, "Bundle no es nulo")
        }

        binding.button.setOnClickListener {
            binding.textView.text = "Etiqueta cambiada"
        }

        /*
        boton.setOnClickListener{
            etiqueta.text = "Etiqueta cambiada"
        }
         */
    }

    override fun onSaveInstanceState (outState : Bundle){
        super.onSaveInstanceState(outState)
        Log.d(TAG, "Estoy en OnCreate")
        if (outState==null){
            Log.d(TAG, "Bundle es nulo")
        }
        else{
            Log.d(TAG, "Bundle no es nulo")
        }
        outState.putString(ETIQUETA, binding.textView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle){
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "Estoy en OnCreate")
        if (savedInstanceState==null){
            Log.d(TAG, "Bundle es nulo")    
        }
        else{
            Log.d(TAG, "Bundle no es nulo")
        }
        binding.textView.text = savedInstanceState.getString(ETIQUETA)
    }
}