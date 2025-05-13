package edu.cnt.developer.profe

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       /* val urlRemota:String = "https://cntg.xunta.gal/"
        val intentImplicito = Intent(Intent.ACTION_VIEW, Uri.parse(urlRemota))
        //TODO REVISAR ESTE CÓDIGO Y LOS INTENTS COMUNES DOC OFICIAL
        if (intentImplicito.resolveActivity(packageManager) != null) {
            startActivity(intentImplicito)
        } else {
            Log.d("MIAPP", "NO hay ninguna app que pueda ver esa web")
        }*/

        //val intent = Intent(this, ListaUsuariosActivity::class.java)
        val intent = Intent(this, PerrosActivity::class.java)
        startActivity(intent)

    }
}