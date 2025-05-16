package edu.cnt.developer.profe.perros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import edu.cnt.developer.profe.R

class PerroFragment: Fragment() {

    //preparamos la vista del fragmento
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemPerro = inflater.inflate(R.layout.perro_fragment, container, false)
        //TODO pendiente Rellenar el fragment con los datos concretos de un perro
        val urlFoto = arguments?.getString("URL_FOTO")
        val textoLeyenda = arguments?.getString("TEXTO_LEYENDA")

        val imgPerro = itemPerro.findViewById<ImageView>(R.id.fotoPerro)
        val textoPerro = itemPerro.findViewById<TextView>(R.id.leyendaPerro)

        textoPerro.text = textoLeyenda
        Picasso.get().load(urlFoto).into(imgPerro)

        return itemPerro
    }
}