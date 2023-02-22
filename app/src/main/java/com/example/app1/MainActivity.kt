package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()//escondemos la barra superior para que no aparezca

        val boton_horarios = findViewById<Button>(R.id.btn_horarios)
        val boton_examenes = findViewById<Button>(R.id.btn_examenes)

        boton_horarios.setOnClickListener{
            /*
            Aqui vamos a crear una variable que almacene el texto que queremos transmitir
            lo metemos en el intent para migrar a otra actividad y lo pasamos para que lo reciba como un bundle
             */
            val texto_h = "Horarios"
            val intent = Intent(this, HorariosActivity::class.java)
            intent.putExtra("objetivo", texto_h)

            startActivity(intent)
        }

        boton_examenes.setOnClickListener{
            val texto_e = "Exámenes"
            val intent = Intent(this, HorariosActivity::class.java)
            intent.putExtra("objetivo", texto_e)

            startActivity(intent)
        }
    }
}