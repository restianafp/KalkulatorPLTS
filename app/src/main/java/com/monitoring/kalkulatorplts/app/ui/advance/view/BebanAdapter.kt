package com.monitoring.kalkulatorplts.app.ui.advance.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.app.ui.advance.model.BebanData
import org.w3c.dom.Text

class BebanAdapter(val c :Context, val bebanList: ArrayList<BebanData>) : RecyclerView.Adapter<BebanAdapter.BebanViewHolder>() {
    inner class BebanViewHolder(val v:View):RecyclerView.ViewHolder(v){
        val jenis = v.findViewById<TextView>(R.id.data_beban)
        val sumber = v.findViewById<TextView>(R.id.data_sumber)
        val daya = v.findViewById<TextView>(R.id.data_daya)
        val durasi = v. findViewById<TextView>(R.id.data_durasi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BebanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_beban,parent,false)
        return BebanViewHolder(v)
    }

    override fun onBindViewHolder(holder: BebanViewHolder, position: Int) {
        val newList = bebanList[position]
        holder.jenis.text = newList.jenisBeban
        holder.sumber.text = newList.sumberBeban
        holder.daya.text = newList.dayaBeban
        holder.durasi.text = newList.durasiBeban
    }

    override fun getItemCount(): Int {
      return bebanList.size
    }

}