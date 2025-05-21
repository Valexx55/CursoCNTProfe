package edu.cnt.developer.profe.mapa

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.HandlerThread
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.cnt.developer.profe.R
import edu.cnt.developer.profe.databinding.ActivityMapsBinding
import java.util.Locale

/**
 *
 * 1 instalar librerias maps, location
 * 2 conseguir el api key
 * 3 creamos mapActivity (elegimos de la galería la plantilla de mapas view)
 * 4 tocadoo el layout FloatinActionButton
 * 5 comprobamos permisos de acceso (Manifest, y en tiempo de ejecución)
 * 6 miro si la ubicación está activa (GPS activo)
 * 7 si no, lo pido (startActivityForResult)
 * 8 SI PERMISOS Y GPS ACTIVO --> fusedLocationProvider (request, callback, looper??)
 * 9 obtenemos ubicación, pintamos el mapa, obtenemos la dirección física
 */

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    lateinit var locationManager: LocationManager //puedo comprobar si tengo activo o no el GPS
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient //este me da la ubicación
    lateinit var locationRequest: LocationRequest //petición de ubicación
    lateinit var locationCallback: LocationCallback //respuesta a la petición de ubicación


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        this.locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun mostrarUbicacionMapa(view: View) {
        //PEDIR PERMISOS.
        //DARLE UNA EXPLICACIÓN ALERTDIALOG
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 535)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            Log.d("MIAPP", "Permisos concedidos")
            //si está el gps activo
            if (estaActivoElGps())
            {
                //accedo a la ubicación
                accederALaUbicacionGPS()
            } else {
                //pedirle que active la ubicación del GPS
                solicitarActivacionGPS ()
            }



        } else {
            Log.d("MIAPP", "Permisos denegados")
            Toast.makeText(this, "SIN ACCESO A LA UBICACIÓN", Toast.LENGTH_LONG).show()
        }

    }

    private fun solicitarActivacionGPS() {
        val intentAjustesGPS = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivityForResult(intentAjustesGPS, 300)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (estaActivoElGps())
        {
            Log.d("MIAPP", "El usuario ha activado el GPS")
            //acceder a la ubicación
            accederALaUbicacionGPS()
        } else {
            Log.d("MIAPP", "El usuario NO ha activado el GPS")
            Toast.makeText(this, "GPS-DESACTIVADO Sin acceso a la ubicación", Toast.LENGTH_LONG).show()
        }

    }

    //@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun accederALaUbicacionGPS() {
       this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        //creo la peticion
        this.locationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(5000)
        //la respuesta
        locationCallback = object : LocationCallback(){
            override fun onLocationResult(resultadoUbicacion: LocationResult) {
                //super.onLocationResult(p0)
               Log.d("MIAPP", "Ubicación obtenida = ${resultadoUbicacion.lastLocation}")
                //TODO parar las peticiones
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)
                //TODO
                actualizarPosicionDelMapa (resultadoUbicacion.lastLocation)


            }
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {

            /*val handlerThread: HandlerThread = HandlerThread("MyHandlerThread")
            handlerThread.start()
            val looper: Looper = handlerThread.getLooper()*/
            this.fusedLocationProviderClient.requestLocationUpdates(this.locationRequest, this.locationCallback, null)
        }

    }

    private fun actualizarPosicionDelMapa(lastLocation: Location) {
        val ubicacionActual = LatLng(lastLocation.latitude, lastLocation.longitude)
        this.mMap.addMarker(MarkerOptions().position(ubicacionActual).title("ESTOY AQUÍ"))
        this.mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacionActual))
        //MOSTRAR LA DIRECCIÓN POSTAL
        mostrarDireccionPostal (lastLocation)

    }

    private fun mostrarDireccionPostal(lastLocation: Location) {

        val geocoder = Geocoder(this, Locale("es"))
        val direcciones =  geocoder.getFromLocation(lastLocation.latitude, lastLocation.longitude, 1)

        if (direcciones!=null && direcciones.size>0)
        {
            val direccion = direcciones[0]
            Log.d("MIAPP", "Dirección obtenida = ${direccion.getAddressLine(0)}  CP ${direccion.postalCode} LOCALIDAD ${direccion.locality} ")
        }

    }

    private fun estaActivoElGps():Boolean {
        var activadoGPS = false

            activadoGPS = this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        return activadoGPS
    }
}