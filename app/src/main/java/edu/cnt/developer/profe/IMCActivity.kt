package edu.cnt.developer.profe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 *
 * HACER UNA CALCULADORA DEL ÍNDICE DE MASA CORPORAL
 *
 * imc = PESO (KG) /  ALTURA * ALTURA
 * IMC < 16 -> desnutridos
 * IMC >=16 y <18 -> delgados
 * IMC >=18 y <25 -> ideal
 * IMC >=25 Y <31 -> sobrepeso
 * IMC >=31 -> obeso
 *
 */

class IMCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}