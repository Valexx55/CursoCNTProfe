package edu.cnt.developer.profe.realtimedatabase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import edu.cnt.developer.profe.R
import java.util.ArrayList

const val URL_DATABASE = "https://certadev-7b03a-default-rtdb.europe-west1.firebasedatabase.app/"

class ClientesActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_clientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.databaseReference = FirebaseDatabase.getInstance(URL_DATABASE).reference
    }

    fun crearCliente(view: View) {

        val nombre = findViewById<EditText>(R.id.editTextText5).text.toString()
        val edad = findViewById<EditText>(R.id.editTextNumber).text.toString().toLong()

        var cliente = Cliente(edad, nombre)
        //genero clave
        var idclave = this.databaseReference.push().key
        cliente.clave = idclave!!
        //inserto
        this.databaseReference.child("clientes").child(idclave).setValue(cliente).addOnCompleteListener {
            tarea -> Toast.makeText(this, "CLIENTE INSERTADO FIN", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
                exception ->
            Toast.makeText(this, "ERROR AL INSERTAR EL CLIENTE", Toast.LENGTH_LONG).show()
            Log.e("MIAPP", "ERRRor", exception)
        }
    }
    fun listarClientesBD(view: View) {

        this.databaseReference.child("clientes").get().addOnSuccessListener {
            datos ->
            var claveDocumento = datos.key
            Log.d("MIAPP", "Clave documento = $claveDocumento")
            var lista = datos.value as Map<String, Map<String, Any>> //los registros
            var entradas = lista.entries
            var nclis = entradas.size //tamaño del listado recuperado
            Log.d("MIAPP", "Número de clientes = $nclis")
            var listaClientes = ArrayList<Cliente>()
            var clienteAux:Cliente
            entradas.forEach{ //paso de JSON a Lista -- deserializando
                (claveId, valor) ->
                Log.d("MIAPP", "Clave cliente = $claveId")
                val nombre  = valor.get("nombre").toString()
                Log.d("MIAPP", "Nombre = $nombre")
                val edadAux  = valor.get("edad").toString().toLong()
                Log.d("MIAPP", "Edad = $edadAux")
                clienteAux = Cliente(edadAux, nombre, claveId)
                listaClientes.add(clienteAux)

            }
            mostrarClientes(listaClientes)

        }
    }

    private fun mostrarClientes(listaClientes: ArrayList<Cliente>) {
        listaClientes.forEach{
            Log.d("MIAPP", "CLIENTE ${it.toString()}")
        }

    }
}