package com.monitoring.kalkulatorplts.app.ui.advance

import android.annotation.SuppressLint
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.monitoring.kalkulatorplts.app.ui.ViewModel
import com.monitoring.kalkulatorplts.databinding.ActivityHasilAdvanceBinding

class HasilAdvance : AppCompatActivity(){
    private lateinit var binding: ActivityHasilAdvanceBinding
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilAdvanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(Application())
        )[ViewModel::class.java]

        val totalDailyEnergy = intent.getStringExtra("totalDailyEnergy")
        val totalEnergyBatt = intent.getStringExtra("totalEnergyBatt")
        val totalEnergyPv = intent.getStringExtra("totalEnergyPv")
        val dataPsh = intent.getStringExtra("dataPsh")
        val efisiensi = intent.getStringExtra("efisiensi")
        val dodMax = intent.getStringExtra("dodMax")
        val hariOtonom = intent.getStringExtra("hariOtonom")
        val tegangan = intent.getStringExtra("tegangan")
        val teganganUnit = intent.getStringExtra("teganganUnit")
        val rasioPv = intent.getStringExtra("rasioPv")
        val rasioAcDc = intent.getStringExtra("rasioAcDc")

        binding.bebanHarian.setText(totalDailyEnergy.toString() + " Watthour")


        viewModel.calculateDec(efisiensi,totalEnergyPv,totalEnergyBatt)
        viewModel.calculateKapasitasBatt(totalEnergyPv,efisiensi,dodMax,hariOtonom)
        viewModel.calculateKapasitasInverter(dataPsh, rasioPv)
        viewModel.calculateKapasitasPvInverter(rasioAcDc)
        initUi()
    }

    @SuppressLint("SetTextI18n")
    private fun initUi() {
        viewModel.hasilDec.observe(this, Observer {
            binding.konsumsiEnergi.setText(String.format("%.2f",it) + " Watthour")
        })
        viewModel.hasilKapasitasBatt.observe(this, Observer {
            binding.kapasitasBaterai.setText(String.format("%.2f",it) + " Watthour")
        })
        viewModel.hasilKapasitasInverter.observe(this, Observer {
            binding.kapasitasInverter.setText(String.format("%.2f",it)+ " Watt")
            binding.kapasitasPv.setText(String.format("%.2f",it) + " Wattpeak")
        })
        viewModel.hasilKapasitasPvInverter.observe(this, Observer {
            binding.kapasitasPvInverter.setText(String.format("%.2f",it)+ " Wattpeak")
        })
    }


}