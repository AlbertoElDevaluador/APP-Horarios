package com.example.app1
import android.content.Intent

interface DocumentoListaListener {
    fun onDocumentoClickListener(url: String, titulo: String)
}