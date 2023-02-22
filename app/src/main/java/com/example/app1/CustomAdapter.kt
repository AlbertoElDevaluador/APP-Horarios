package com.example.app1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import org.w3c.dom.Text

class CustomAdapter(val Documentos: MutableList<Documento>, val Listener: DocumentoListaListener):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    fun setLista(Lista: MutableList<Documento>)
    {
        this.Documentos.addAll(Lista)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        //Recibe una vista y saca el viewholder
        var itemTitle: TextView
        var itemURL: TextView
        var itemCardView: CardView

        init{
            itemTitle = itemView.findViewById(R.id.titulo_card)
            itemURL = itemView.findViewById(R.id.card_URL)
            itemCardView = itemView.findViewById<CardView>(R.id.cv)//cogemos el cardview
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        //Definimos el layout que vamos a holdear
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = Documentos[i].titulo
        viewHolder.itemURL.text = Documentos[i].grado

        viewHolder.itemCardView.setOnClickListener{
            Listener.onDocumentoClickListener(Documentos[i].url, Documentos[i].titulo)
        }
    }

    override fun getItemCount(): Int {
        return Documentos.size
    }
}