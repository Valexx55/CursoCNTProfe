package edu.cnt.developer.profe.perros

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterPerrosFragment(fragmentActivity:FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    var listadoPerros:ListadoPerros? = null

    override fun getItemCount(): Int {
        return listadoPerros!!.message.size ?: 5//con esto, kotlin no chequea la posiblidad de que el listado sea nulo
    }

    override fun createFragment(position: Int): Fragment {
        var perroFragment : PerroFragment = PerroFragment()

        val urlFoto = listadoPerros?.message?.get(position)
        val posicion = position+1//para llevar un contador en la leyenda
        val textoLeyenda = "$posicion de ${listadoPerros?.message?.size}"

        var bundle = Bundle()
        bundle.putString("URL_FOTO", urlFoto)
        bundle.putString("TEXTO_LEYENDA", textoLeyenda)

        perroFragment.arguments = bundle//estos valores, están disponibles cuando se entre en el método onCreateView del Fragment


        return perroFragment
    }
}