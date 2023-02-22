package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent


class HorariosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horarios)

        /*
        Elimino la barra superior
         */
        supportActionBar?.hide()

        /*
        Modifico el texto inicial conforme a lo clickado en la pagina anterior
         */
        var texto_inicio = findViewById<TextView>(R.id.textView2)
        val bundle = intent.extras
        val texto = bundle?.getString("objetivo")

        texto?.let {
            texto_inicio.setText(texto)

            /*
            Inicializamos todos los botones para tenerlos en variables y poder utilizarlos
             */
            val boton_GIA = findViewById<Button>(R.id.button)
            val boton_GIDIDP = findViewById<Button>(R.id.button2)
            val boton_GIE = findViewById<Button>(R.id.button3)
            val boton_GIEI = findViewById<Button>(R.id.button4)
            val boton_GII = findViewById<Button>(R.id.button5)
            val boton_GIM = findViewById<Button>(R.id.button6)
            val boton_GITI = findViewById<Button>(R.id.button7)

            //Voy a dar utilidad a todos los botones que aparecen
            boton_GIA.setOnClickListener()
            {
                IrMostrarDocumento(texto, "GIA")
            }

            boton_GIDIDP.setOnClickListener()
            {
                IrMostrarDocumento(texto, "GIDIDP")
            }

            boton_GIE.setOnClickListener()
            {
                IrMostrarDocumento(texto, "GIE")
            }

            boton_GIEI.setOnClickListener()
            {
                IrMostrarDocumento(texto, "GIEI")
            }

            boton_GII.setOnClickListener()
            {
                IrMostrarDocumento(texto, "GII")
            }

            boton_GIM.setOnClickListener()
            {
                IrMostrarDocumento(texto, "GIM")
            }

            boton_GITI.setOnClickListener()
            {
                IrMostrarDocumento(texto, "GITI")
            }
        }
    }

    fun IrMostrarDocumento(Tipo: String, Grado: String)
    {
        val intent = Intent(this, MostrarDocumento::class.java)
        intent.putExtra("Tipo", Tipo)
        intent.putExtra("Grado", Grado)

        startActivity(intent)
    }
}