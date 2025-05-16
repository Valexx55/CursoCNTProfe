package edu.cnt.developer.profe.perros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        return itemPerro
    }
}