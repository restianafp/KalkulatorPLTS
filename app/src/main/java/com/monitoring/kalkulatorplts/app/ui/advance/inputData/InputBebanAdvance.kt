package com.monitoring.kalkulatorplts.app.ui.advance.inputData

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.app.data.BebanData
import com.monitoring.kalkulatorplts.app.ui.ViewModel
import com.monitoring.kalkulatorplts.app.ui.advance.InputDataAdvance
import com.monitoring.kalkulatorplts.databinding.ActivityInputBebanAdvanceBinding

class InputBebanAdvance : AppCompatActivity(), iCommunicator {
    private lateinit var binding: ActivityInputBebanAdvanceBinding
    private lateinit var bebanAdapter: BebanAdapter
    private lateinit var viewModel: ViewModel
    private var totalDailyEnergy: Int = 0
    private var totalEnergyBatt: Int = 0
    private var totalEnergyPv: Int = 0
    private var dataPsh: Double = 0.0
    private lateinit var beban:BebanData
    private lateinit var listDaerah: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBebanAdvanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Init Adapter //
        bebanAdapter = BebanAdapter(this, ArrayList())
        binding.rvBeban.layoutManager = LinearLayoutManager(this)
        binding.rvBeban.adapter = bebanAdapter


        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(Application())
        )[ViewModel::class.java]



        binding.btnNext.setOnClickListener {
            if(dataPsh == 0.0){
                binding.etPilihLokasi.error = "Pilih lokasi"
            }
            if (totalDailyEnergy == 0){
                Toast.makeText(this, "Anda belum memasukan beban",Toast.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this, InputDataAdvance::class.java)
                intent.putExtra("totalDailyEnergy", totalDailyEnergy.toString())
                intent.putExtra("totalEnergyBatt", totalEnergyBatt.toString())
                intent.putExtra("totalEnergyPv", totalEnergyPv.toString())
                intent.putExtra("dataPsh", dataPsh.toString())
                startActivity(intent)
            }
        }

        binding.btnAddDevice.setOnClickListener {
            showDialogTambahBeban()
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }


        getEnergy()
        initUi()

        bebanAdapter.setOnCLickListener(object : BebanAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                viewModel.remove(position)
            }

        })


    }

    override fun onResume() {
        super.onResume()
        showLokasiDropdown()
    }

    private fun showLokasiDropdown() {
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

    private fun getEnergy() {
        viewModel.totalEnergyBatt.observe(this, {
            totalEnergyBatt = it
        })
        viewModel.totalEnergyPv.observe(this, {
            totalEnergyPv = it
        })
        viewModel.totalDailyEnergy.observe(this, {
            totalDailyEnergy = it
        })
    }

    private fun showDialogTambahBeban() {
        val addBebanDialogFragment = TambahBebanDialog()
        addBebanDialogFragment.show(supportFragmentManager, TambahBebanDialog::class.java.simpleName)
    }

    private fun initUi() {
        viewModel.listBeban.observe(this,  {
            bebanAdapter.updateList(it)
        })
    }

    override fun getBeban(bebanData: BebanData) {
        beban = bebanData
        viewModel.calculateEnergy(beban)
        viewModel.addBeban(beban)

    }



}