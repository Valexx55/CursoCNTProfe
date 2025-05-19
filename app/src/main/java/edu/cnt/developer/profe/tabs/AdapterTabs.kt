package edu.cnt.developer.profe.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterTabs(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    val arrayDatos = intArrayOf(1, 2, 3)

    override fun getItemCount(): Int {
        return this.arrayDatos.size
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment

            fragment = FragmentoTabs()
            val  bundle = Bundle()
            bundle.putInt("VALOR", arrayDatos[position])
            fragment.arguments = bundle

        return fragment
    }

}