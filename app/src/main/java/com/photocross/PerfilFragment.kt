package com.photocross

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PerfilFragment : Fragment() {

    lateinit var recyclerview : RecyclerView

    companion object {
        fun newInstance(): PerfilFragment = PerfilFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootview = inflater.inflate(R.layout.fragment_perfil, container, false)
        recyclerview = rootview.findViewById(R.id.recyclerGaleria) as RecyclerView
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = AdapterGaleria()
        return rootview

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFotosGaleria()
    }

    fun getFotosGaleria(): MutableList<FotosGaleria>{
        var fotosgaleria : MutableList<FotosGaleria> = ArrayList()
        fotosgaleria.add(FotosGaleria("Foto Nova", "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"))
        return fotosgaleria
    }
}

class AdapterGaleria : RecyclerView.Adapter<AdapterGaleria.ViewHolder>() {

    var fotosgaleria: MutableList<FotosGaleria> = ArrayList()
    lateinit var context: Context

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = fotosgaleria[position]
        holder.bind(item, context)

    }

    override fun getItemCount(): Int {
        return fotosgaleria.size

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterGaleria.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.celda_galeria, parent, false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nomFoto = view.findViewById(R.id.tvFotoGaleria) as TextView
        val fotoGaleria = view.findViewById(R.id.imagenGaleria) as ImageView

        fun bind(fotosgaleria: FotosGaleria, context: Context) {

            nomFoto.text = fotosgaleria.nombrefoto
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(
                    context, fotosgaleria.nombrefoto,
                    Toast.LENGTH_LONG
                ).show()
            })
            fotoGaleria.loadUrl(fotosgaleria.foto)
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }
    }
}

