package com.monitoring.kalkulatorplts.app.ui.advance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.databinding.ActivityInputDataAdvanceBinding

class InputDataAdvance : AppCompatActivity() {
    private lateinit var binding: ActivityInputDataAdvanceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDataAdvanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this,HasilAdvance::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        showDropdownTeganganBaterai()
        showDropdownTeganganBateraiUnit()
        showDropdownRasioPerforma()
        showDropdownRasioACDC()
    }

    private fun showDropdownRasioACDC() {

        val dataRasioACDC = resources.getStringArray(R.array.data_rasio_acdc)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataRasioACDC)
        binding.etRasioAcdc.setAdapter(adapter)

        binding.etRasioAcdc.setOnItemClickListener { adapterView, view, position, id ->
            val selectedValue = adapter.getItem(position)
            Toast.makeText(this, selectedValue, Toast.LENGTH_LONG).show()
        }
    }

    private fun showDropdownRasioPerforma() {
        val dataRasioPerforma = resources.getStringArray(R.array.data_rasio_performa)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataRasioPerforma)
        binding.etRasioPerforma.setAdapter(adapter)

        binding.etRasioPerforma.setOnItemClickListener { adapterView, view, position, id ->
            val selectedValue = adapter.getItem(position)
            Toast.makeText(this, selectedValue, Toast.LENGTH_LONG).show()
        }
    }

    private fun showDropdownTeganganBateraiUnit() {
        val dataTeganganBaterai = resources.getStringArray(R.array.data_tegangan_baterai_unit)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataTeganganBaterai)
        binding.etTeganganBateraiUnit.setAdapter(adapter)

        binding.etTeganganBateraiUnit.setOnItemClickListener { adapterView, view, position, id ->
            val selectedValue = adapter.getItem(position)
            Toast.makeText(this, selectedValue, Toast.LENGTH_LONG).show()
        }
    }

    private fun showDropdownTeganganBaterai() {
        val dataTeganganBaterai = resources.getStringArray(R.array.data_tegangan_baterai)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataTeganganBaterai)
        binding.etTeganganBaterai.setAdapter(adapter)

        binding.etTeganganBaterai.setOnItemClickListener { adapterView, view, position, id ->
            val selectedValue = adapter.getItem(position)
            Toast.makeText(this, selectedValue, Toast.LENGTH_LONG).show()
        }
    }
}