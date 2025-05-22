package edu.cnt.developer.profe.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import edu.cnt.developer.profe.R

class LoginActivity : AppCompatActivity() {

    lateinit var fireBaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.fireBaseAuth = FirebaseAuth.getInstance()
    }

    fun login(view: View) {

        val correo = findViewById<EditText>(R.id.editTextText3).text.toString()
        val pwd = findViewById<EditText>(R.id.editTextText4).text.toString()

        Log.d("MIAPP", "COMPROBANDO SI EXSITE usuario $correo $pwd")
        this.fireBaseAuth.signInWithEmailAndPassword(correo, pwd).addOnCompleteListener {
            resultado ->
            if (resultado.isSuccessful)
            {
                Log.d("MIAPP", "EL USUARIO EXSITE !")
                Toast.makeText(this, "EL USUARIO EXSITE !", Toast.LENGTH_LONG).show()
            } else {

                Log.d("MIAPP", "EL USUARIO NO EXSITE !- O ERROR")
                Log.e("MIAPP", resultado.exception.toString(), resultado.exception)
                Toast.makeText(this, "EL USUARIO NO EXSITE ! - ERROR", Toast.LENGTH_LONG).show()
            }
        }

    }
}