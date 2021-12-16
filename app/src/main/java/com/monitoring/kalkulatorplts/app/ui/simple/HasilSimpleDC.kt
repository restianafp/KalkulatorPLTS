package com.monitoring.kalkulatorplts.app.ui.simple

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.app.ui.ViewModel
import com.monitoring.kalkulatorplts.databinding.ActivityHasilSimpleDcBinding

class HasilSimpleDC : AppCompatActivity() {
    private lateinit var binding: ActivityHasilSimpleDcBinding
    private lateinit var viewModel: ViewModel
    private var bebanHarian = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilSimpleDcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(Application())
        )[ViewModel::class.java]

        val totalBeban = intent.getStringExtra("totalBeban")
        val dataPsh = intent.getStringExtra("dataPsh")

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        viewModel.convertEnergytoWh(totalBeban)
        viewModel.calculateDecSimple()
        viewModel.calculateKapasitasBattSimple()
        viewModel.calculateJumlahBatt("24","100")
        viewModel.calculateKapasitasInverterSimple(dataPsh)

        initUi()
    }

    private fun initUi() {
        viewModel.totalDailyEnergy.observe(this, {
            bebanHarian = it.toString()
            binding.bebanHarian.setText(it.toString() + " Watt")
        })
        viewModel.hasilDec.observe(this, {
            binding.konsumsiEnergi.setText(String.format("%.2f",it) + " Watthour")
        })
        viewModel.hasilKapasitasBatt.observe(this,{
            binding.kapasitasBaterai.setText(String.format("%.2f",it) + " Watthour")
        })
        viewModel.totalUnitBatt.observe(this,{
            binding.jumlahBaterai.setText(it.toString()  + " Unit" )
        })
        viewModel.hasilKapasitasInverter.observe(this,{
            binding.kapasitasPv.setText(String.format("%.2f",it)+ " Wattpeak")
        })
    }
}