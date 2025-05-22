package edu.cnt.developer.profe.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.cnt.developer.profe.R

class AutenticacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun aNuevaCuenta(view: View) {
        val intentNueva = Intent(this, SignupActivity::class.java)
        startActivity(intentNueva)
        finish()
    }
    fun acceder(view: View) {
        val intentLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentLogin)
        finish()

    }
}