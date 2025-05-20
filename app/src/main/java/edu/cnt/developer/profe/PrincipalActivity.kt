package edu.cnt.developer.profe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import edu.cnt.developer.fechayhora.FechaYHoraActivity
import edu.cnt.developer.profe.imc.IMCActivity
import edu.cnt.developer.profe.perros.PerrosActivity
import edu.cnt.developer.profe.productos.ProductosActivity
import edu.cnt.developer.profe.tabs.TabsActivity
import edu.cnt.developer.profe.usuarios.ListaUsuariosActivity

class PrincipalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    var menuvisible : Boolean = false //controlar el estado del menú

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_principal)
        this.drawerLayout = findViewById(R.id.drawer)
        this.navigationView = findViewById(R.id.navview)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)//para dibujar el icono del menu
        supportActionBar?.setHomeAsUpIndicator(R.drawable.hamburguesa)//el icono

        this.navigationView.setNavigationItemSelectedListener(this)

       /* val urlRemota:String = "https://cntg.xunta.gal/"
        val intentImplicito = Intent(Intent.ACTION_VIEW, Uri.parse(urlRemota))
        //TODO REVISAR ESTE CÓDIGO Y LOS INTENTS COMUNES DOC OFICIAL
        if (intentImplicito.resolveActivity(packageManager) != null) {
            startActivity(intentImplicito)
        } else {
            Log.d("MIAPP", "NO hay ninguna app que pueda ver esa web")
        }*/

        //val intent = Intent(this, ListaUsuariosActivity::class.java)
        // val intent = Intent(this, PerrosActivity::class.java)
        //val intent = Intent(this, ProductosActivity::class.java)
        //startActivity(intent)

    }

    /**
     * este método se invoca cuando se toca la hamburguesa
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId)
        {
            android.R.id.home -> {
                Log.d("MIAPP", "Tocado el botón hamburguesa")
                if (this.menuvisible)
                {
                    this.drawerLayout.closeDrawers()
                } else {
                    this.drawerLayout.openDrawer(GravityCompat.START)
                }
                this.menuvisible = !this.menuvisible
            }
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * Esta función se invoca cuando el usuario toca una opción del menú desplegado
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("MIAPP", "MENÚ LATERAL TOCADO")
        var objetoClass : Class<*>? = null
        when (item.order)
        {
            0 -> {objetoClass = VersionesActivity::class.java}
            1 -> {objetoClass = AdivinaActivity::class.java}
            2 -> {objetoClass = IMCActivity::class.java}
            3 -> {objetoClass = ListaUsuariosActivity::class.java}
            4 -> {objetoClass = WebViewActivity::class.java}
            5 -> {objetoClass = ProductosActivity::class.java}
            6 -> {objetoClass = PerrosActivity::class.java}
            7 -> {objetoClass = TabsActivity::class.java}
            8 -> {objetoClass = FechaYHoraActivity::class.java}

        }
        this.drawerLayout.closeDrawers()
        val intent = Intent(this, objetoClass)
        startActivity(intent)

        return super.onOptionsItemSelected(item)
    }
}