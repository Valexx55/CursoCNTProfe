package edu.cnt.developer.profe

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//en esta actividad, vamos a representar una web
class WebViewActivity : AppCompatActivity() {

    val urlRemota:String = "https://cntg.xunta.gal/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        var webView = findViewById<WebView>(R.id.miwebview)
        webView.loadUrl(urlRemota)
        webView.settings.javaScriptEnabled = true

    }
}