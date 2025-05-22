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

class SignupActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.firebaseAuth = FirebaseAuth.getInstance()
    }

    fun registrarNuevoUsuario(view: View) {
        val correo = findViewById<EditText>(R.id.editTextText).text.toString()
        val pwd = findViewById<EditText>(R.id.editTextText2).text.toString()

        this.firebaseAuth.createUserWithEmailAndPassword(correo, pwd).addOnCompleteListener {
                resultado ->
            if (resultado.isSuccessful)
            {
                Log.d("MIAPP", "USUARIO REGISTRADO CON ÉXITO !")
                Toast.makeText(this, "USUARIO REGISTRADO CON ÉXITO !", Toast.LENGTH_LONG).show()
            } else {

                Log.d("MIAPP", "ERROR AL REGISTRAR EL NUEVO USUARIO")
                Log.e("MIAPP", resultado.exception.toString(), resultado.exception)
                Toast.makeText(this, "ERROR AL REGISTRAR EL NUEVO USUARIO", Toast.LENGTH_LONG).show()
            }

        }

    }
}