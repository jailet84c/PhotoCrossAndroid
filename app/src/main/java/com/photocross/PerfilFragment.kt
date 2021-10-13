package com.photocross

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class PerfilFragment : Fragment() {

    lateinit var recyclerview : RecyclerView
    val adaptador = AdapterGaleria()
    private var imagengaleria: ImageView? = null
    private var mUri: Uri? = null
    private lateinit var botoGaleria : Button
    private val OPERATION_CHOOSE_PHOTO = 1

    companion object {
        fun newInstance(): PerfilFragment = PerfilFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val vista = inflater.inflate(R.layout.fragment_perfil, container, false)
        recyclerview = vista.findViewById(R.id.recyclerGaleria)
        val botoGaleria = vista.findViewById<View>(R.id.galeriabutton) as FloatingActionButton
        botoGaleria.setOnClickListener{EscogerFoto()}
        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adaptador
        }

    }

    private fun EscogerFoto() {

        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, OPERATION_CHOOSE_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}

class AdapterGaleria : RecyclerView.Adapter<AdapterGaleria.ViewHolder>() {

    private val kode = arrayOf("d116df5",
        "36ffc75", "f5cfe78", "5b87628",
        "db8d14e", "9913dc4", "e120f96",
        "466251b")

    private val kategori = arrayOf("Kekayaan", "Teknologi",
        "Keluarga", "Bisnis",
        "Keluarga", "Hutang",
        "Teknologi", "Pidana")

    private val isi = arrayOf("pertanyaan 9",
        "pertanyaan 11", "pertanyaan 17", "test forum",
        "pertanyaan 12", "pertanyaan 18", "pertanyaan 20",
        "pertanyaan 21")


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemKode.text = kode[position]
        holder.itemKategori.text = kategori[position]
        holder.itemIsi.text = isi[position]
    }

    override fun getItemCount(): Int {
        return kode.size

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.celda_galeria, parent, false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var itemKode: TextView = itemView.findViewById(R.id.kodePertanyaan)
        var itemKategori: TextView = itemView.findViewById(R.id.kategori)
        var itemIsi: TextView = itemView.findViewById(R.id.isiPertanyaan)

        init {

            /* itemView.setOnClickListener {
                 var position: Int = adapterPosition
                 val context = itemView.context
                 val intent = Intent(context, DetailPertanyaan::class.java).apply {
                     putExtra("NUMBER", position)
                     putExtra("CODE", itemKode.text)
                     putExtra("CATEGORY", itemKategori.text)
                     putExtra("CONTENT", itemIsi.text)
                 }
                 context.startActivity(intent) */
            }
        }
    }


