package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.net.Uri
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MostrarDocumento : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_mostrar_documento)
        /*
        Elimino la barra superior
         */

        //----------------------------------------------------------------------------------------------//
        //Valores del intent de la pagina anterior, el tipo de dato requerido, y el grado
        val Tipo = intent.extras?.getString("Tipo")
        val Grado = intent.extras?.getString("Grado")

        //Establecemos la el texto superior
        val Texto_tipo = findViewById<TextView>(R.id.textView4)
        Texto_tipo.setText(Tipo)

//----------------------------------------------------------------------------------------------//
        //Creamos la lista que vamos a pasar al adaptador una vez y luego otra pasando los datos reales
        var Lista = mutableListOf<Documento>()

        //inicializo la base de datos

        val db = FirebaseFirestore.getInstance()

        //--------------------------inicializamos el recycle--------------------------------------------------------------

        val recycler = findViewById<RecyclerView>(R.id.Recycle)//cogemos el recycle para tenerlo trackeado

        val adapter = CustomAdapter(Lista, object : DocumentoListaListener{
            override fun onDocumentoClickListener(url: String, titulo: String) {
                var url_uri = Uri.parse(url)//obtenemos la url del tipo uri
                var intent = Intent(Intent.ACTION_VIEW, url_uri)//para ir a una web donde descargar el pdf

                //var intent = Intent(this@MostrarDocumento, WebViewer::class.java)
                //intent.putExtra("url", url)
                //intent.putExtra("titulo", titulo)

                startActivity(intent)//mandamos el intent para migrar a la web
            }
        })

        //inicializamos el adaptador con una lista vacia y llamamos al recycleview
        recycler.layoutManager = LinearLayoutManager(this)//pasamos el contexto al recycle
        recycler.adapter = adapter

        //---------------------GlobalScope para ver que se esperan los tiempos de la bd-------------------------------
        GlobalScope.launch(Dispatchers.IO) {

            when(Tipo)
            {
                "Horarios" -> {
                    db.collection("Documentos").get()
                        .addOnSuccessListener {
                                fbdocuments -> for(docu in fbdocuments)
                        {
                            val docu_lista = docu.toObject(Documento::class.java)
                            Lista.add(docu_lista)

                        }
                            Log.d("Lista de documentos: ", "${Lista}")
                        }
                        .await()
                }

                "Exámenes" -> {
                    db.collection("Examenes").get()
                        .addOnSuccessListener {
                                fbdocuments -> for(docu in fbdocuments)
                        {
                            val docu_lista = docu.toObject(Documento::class.java)
                            Lista.add(docu_lista)

                        }
                            Log.d("Lista de documentos: ", "${Lista}")
                        }
                        .await()
                }
            }


            runOnUiThread{
                adapter.setLista(Lista)
            }

        }
        Log.e("Lista documentos: ", "${Lista}")
    }
}