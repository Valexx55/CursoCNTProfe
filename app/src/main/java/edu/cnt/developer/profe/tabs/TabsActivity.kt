package edu.cnt.developer.profe.tabs

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import edu.cnt.developer.profe.R

class TabsActivity : AppCompatActivity(), TabLayoutMediator.TabConfigurationStrategy {

    lateinit var viewPager2: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var adapterTabs: AdapterTabs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)
        this.viewPager2 = findViewById(R.id.vpt)
        this.tabLayout = findViewById(R.id.tablayout)
        this.adapterTabs = AdapterTabs(this)

        this.viewPager2.adapter = this.adapterTabs
        //asociar el tablayout con el viewpager
        TabLayoutMediator(tabLayout, viewPager2, this).attach()

    }

    //este método se llama automáticmente tras crearse cada TAB. Al iniciar cada pestaña
    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        tab.text = "VISTA ${position+1}"
        Log.d("MIAPP", "onConfigureTab")
    }
}