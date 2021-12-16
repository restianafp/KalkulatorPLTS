package com.monitoring.kalkulatorplts.app.ui.advance.inputData

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.app.data.BebanData
import com.monitoring.kalkulatorplts.app.ui.ViewModel

class BebanAdapter(val c :Context, val bebanList: ArrayList<BebanData>) : RecyclerView.Adapter<BebanAdapter.BebanViewHolder>() {

    private lateinit var mListener:onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnCLickListener(listener: onItemClickListener){
        mListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BebanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_beban,parent,false)
        return BebanViewHolder(v, mListener)


    }


    override fun onBindViewHolder(holder: BebanViewHolder, position: Int) {
        val newList = bebanList[position]
        holder.jenis.text = newList.jenisBeban[0].uppercase() + newList.jenisBeban.lowercase().substring(1)
        holder.sumber.text = newList.sumberBeban
        holder.daya.text = newList.dayaBeban.toString() + " Watt"
        holder.durasi.text = newList.durasiBeban + " Jam"


    }

    override fun getItemCount(): Int {
      return bebanList.size
    }

    fun updateList(newList: List<BebanData>){
        bebanList.clear()
        bebanList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class BebanViewHolder(val v:View, listener: onItemClickListener)
        :RecyclerView.ViewHolder(v) {
        val jenis = v.findViewById<TextView>(R.id.data_beban)
        val sumber = v.findViewById<TextView>(R.id.data_sumber)
        val daya = v.findViewById<TextView>(R.id.data_daya)
        val durasi = v.findViewById<TextView>(R.id.data_durasi)
        val btnHapus = v.findViewById<TextView>(R.id.hapusTv)
        init {
            btnHapus.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }






}