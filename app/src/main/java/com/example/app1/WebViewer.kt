package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import org.w3c.dom.Text

class WebViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_web_viewer)

        var bundle = intent.extras

        var titulo = findViewById<TextView>(R.id.titulo)
        val web = findViewById<WebView>(R.id.webviewer)
        var url = bundle?.getString("url")

        titulo.text = bundle?.getString("titulo")


        url?.let{
            web.loadUrl(url)
        }


    }
}