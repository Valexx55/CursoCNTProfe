package edu.cnt.developer.profe.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import edu.cnt.developer.profe.R

class FragmentoTabs:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var fragment:View? = null

            fragment = inflater.inflate(R.layout.fragment_tab, container, false)
            val valor = arguments?.getInt("VALOR")
            val tv = fragment.findViewById<TextView>(R.id.textFragment)
            tv.text = "VISTA $valor"
        //TODO conectar TabsActivity (ViewPager) con AdapterTabs y con FragmentoTabs

        return fragment
    }
}