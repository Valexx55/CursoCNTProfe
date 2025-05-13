package edu.cnt.developer.profe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaUsuariosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_usuarios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var usuario1: Usuario = Usuario("Vale", 42)
        var usuario2: Usuario = Usuario("Pepa", 18)
        var usuario3: Usuario = Usuario("José", 36)
        var usuario4: Usuario = Usuario("Juani", 12)
        var usuario5: Usuario = Usuario("Manolo", 81)

        var listaUsuarios = listOf(usuario5, usuario4, usuario3, usuario2, usuario1)

        //TODO MOSTRAR LA LISTA EN PANTALLA
        //mostrar una colección -> RecyclerView / ListView
        var recview = findViewById<RecyclerView>(R.id.listaPersonasRecView)
        //adapter
        var adapterUsuarios = UsuariosAdapter(listaUsuarios)
        recview.adapter = adapterUsuarios//Asocio al recycler su apadter
        var layoutRecycler : RecyclerView.LayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false )
        //var layoutRecycler : RecyclerView.LayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false )
        //var layoutRecycler : RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recview.layoutManager = layoutRecycler
    }


}