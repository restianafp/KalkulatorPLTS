package com.monitoring.kalkulatorplts.app.ui.advance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.app.ui.ViewModel
import com.monitoring.kalkulatorplts.databinding.ActivityInputDataAdvanceBinding

class InputDataAdvance : AppCompatActivity() {

    private lateinit var binding: ActivityInputDataAdvanceBinding
    private lateinit var viewModel: ViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataAdvanceBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val totalDailyEnergy = intent.getStringExtra("totalDailyEnergy")
        val totalEnergyBatt = intent.getStringExtra("totalEnergyBatt")
        val totalEnergyPv = intent.getStringExtra("totalEnergyPv")
        val dataPsh = intent.getStringExtra("dataPsh")



        binding.btnNext.setOnClickListener {
            if (isFormNotEmpty()){
                val efisiensi = binding.etEfisiensiBaterai.text.toString()
                val dodMax = binding.etDodMax.text.toString()
                val hariOtonom = binding.etHariOtonom.text.toString()
                val tegangan = binding.etTeganganBaterai.text.toString()
                val teganganUnit = binding.etTeganganBateraiUnit.text.toString()
                val rasioPv = binding.etRasioPerformaPv.text.toString()
                val rasioAcDc = binding.etRasioAcDc.text.toString()

                val intent = Intent(this, HasilAdvance::class.java)
                intent.putExtra("totalDailyEnergy", totalDailyEnergy)
                intent.putExtra("totalEnergyBatt", totalEnergyBatt.toString())
                intent.putExtra("totalEnergyPv", totalEnergyPv.toString())
                intent.putExtra("dataPsh", dataPsh)
                intent.putExtra("efisiensi", efisiensi)
                intent.putExtra("dodMax", dodMax)
                intent.putExtra("hariOtonom", hariOtonom)
                intent.putExtra("tegangan", tegangan)
                intent.putExtra("teganganUnit", teganganUnit)
                intent.putExtra("rasioPv", rasioPv)
                intent.putExtra("rasioAcDc", rasioAcDc)
                startActivity(intent)
            }

        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onResume() {
        super.onResume()
        showDropdownTeganganBaterai()
        showDropdownTeganganBateraiUnit()
    }


    private fun isFormNotEmpty(): Boolean {
        with(binding){
            if(etEfisiensiBaterai.text.isNullOrEmpty()){
                etEfisiensiBaterai.error = "Isi Efisiensi Baterai"
                return false
            }
            if (etDodMax.text.isNullOrEmpty()){
                etDodMax.error="Isi DOD Max"
                return false
            }
            if(etHariOtonom.text.isNullOrEmpty()){
                etHariOtonom.error = "Isi Hari Otonom"
                return false
            }
            if(etTeganganBaterai.text.isNullOrEmpty()){
                etTeganganBaterai.error = "Isi Tegangan Baterai"
                return false
            }
            if(etTeganganBateraiUnit.text.isNullOrEmpty()){
                etTeganganBateraiUnit.error = "Isi Kapasitas Baterai Unit"
                return false
            }
            if(etRasioPerformaPv.text.isNullOrEmpty()){
                etRasioPerformaPv.error = "Isi Rasio Performa PV"
                return false
            }
            if(etRasioAcDc.text.isNullOrEmpty()){
                etRasioAcDc.error = "Isi Rasio Performa AC/DC"
                return false
            }
            return true
        }

    }


    private fun showDropdownTeganganBateraiUnit() {
        val dataTeganganBaterai = resources.getStringArray(R.array.data_tegangan_baterai_unit)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataTeganganBaterai)
        binding.etTeganganBateraiUnit.setAdapter(adapter)

    }

    private fun showDropdownTeganganBaterai() {
        val dataTeganganBaterai = resources.getStringArray(R.array.data_tegangan_baterai)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataTeganganBaterai)
        binding.etTeganganBaterai.setAdapter(adapter)

    }
}