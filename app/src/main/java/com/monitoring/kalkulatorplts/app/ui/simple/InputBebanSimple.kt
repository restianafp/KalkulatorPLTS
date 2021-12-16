package com.monitoring.kalkulatorplts.app.ui.simple

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.app.ui.ViewModel
import com.monitoring.kalkulatorplts.databinding.ActivityInputBebanSimpleBinding

class InputBebanSimple : AppCompatActivity() {
    private lateinit var binding:ActivityInputBebanSimpleBinding
    private lateinit var viewModel:ViewModel
    private var dataPsh = 0.0
    private lateinit var listDaerah: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBebanSimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(Application())
        )[ViewModel::class.java]

        binding.btnNext.setOnClickListener {
            if(isFormNotEmpty()){
                showResultSimple()
            }

        }



    }

    override fun onResume() {
        super.onResume()
        showDropdownACDC()
        showDropdownLokasi()
    }

    private fun showDropdownLokasi() {
        viewModel.daerah.observe(this, {
            listDaerah = it
        })
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, listDaerah)
        binding.etPilihLokasi.setAdapter(adapter)

        binding.etPilihLokasi.setOnItemClickListener { adapterView, view, position, id ->
            viewModel.getPsh(position)
            viewModel.dataPsh.observe(this, {
                dataPsh = it.toDouble()
            })
        }
    }

    private fun showDropdownACDC() {
        val dataACDC = resources.getStringArray(R.array.data_acdc)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataACDC)
        binding.etPilihAcDc.setAdapter(adapter)

        binding.etPilihAcDc.setOnItemClickListener { adapterView, view, position, id ->
            val selectedValue = adapter.getItem(position)
            val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply {
                putString("STRING_KEY", selectedValue)
            }.apply()
        }

    }

    private fun showResultSimple() {
        val totalBeban = binding.etTotalEnergi.text.toString()
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)
        if(savedString.equals("AC")){
            val intent = Intent(this,HasilSimpleAC::class.java)
            intent.putExtra("totalBeban", totalBeban)
            intent.putExtra("dataPsh", dataPsh.toString())
            startActivity(intent)
        }else{
            val intent = Intent(this,HasilSimpleDC::class.java)
            intent.putExtra("totalBeban", totalBeban)
            intent.putExtra("dataPsh", dataPsh.toString())
            startActivity(intent)
        }


    }

    private fun isFormNotEmpty(): Boolean {
        with(binding){
            if (etPilihLokasi.text.isNullOrEmpty()){
                etPilihLokasi.error = "Pilih lokasi"
                return false
            }
            if(etPilihAcDc.text.isNullOrEmpty()){
                etPilihAcDc.error = "Pilih arus listrik"
                return false
            }
            if(etTotalEnergi.text.isNullOrEmpty()){
                etTotalEnergi.error = "Isi total energi bulanan Anda"
                return false
            }
            return true
        }
    }

}