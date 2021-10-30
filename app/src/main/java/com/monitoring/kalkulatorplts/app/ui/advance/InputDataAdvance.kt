package com.monitoring.kalkulatorplts.app.ui.advance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.monitoring.kalkulatorplts.R

class InputDataAdvance : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data_advance)
    }

    override fun onResume() {
        super.onResume()

        showDropdownTeganganBaterai()
        showDropdownTeganganBateraiUnit()
        showDropdownRasioPerforma()
        showDropdownRasioACDC()
    }

    private fun showDropdownRasioACDC() {
        val etRasioACDC = findViewById<AutoCompleteTextView>(R.id.et_rasio_acdc)

        val dataRasioACDC = resources.getStringArray(R.array.data_rasio_acdc)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataRasioACDC)
        etRasioACDC.setAdapter(adapter)

        etRasioACDC.setOnItemClickListener { adapterView, view, position, id ->
            val selectedValue = adapter.getItem(position)
            Toast.makeText(this, selectedValue, Toast.LENGTH_LONG).show()
        }
    }

    private fun showDropdownRasioPerforma() {
        val etRasioPerforma = findViewById<AutoCompleteTextView>(R.id.et_rasio_performa)

        val dataRasioPerforma = resources.getStringArray(R.array.data_rasio_performa)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataRasioPerforma)
        etRasioPerforma.setAdapter(adapter)

        etRasioPerforma.setOnItemClickListener { adapterView, view, position, id ->
            val selectedValue = adapter.getItem(position)
            Toast.makeText(this, selectedValue, Toast.LENGTH_LONG).show()
        }
    }

    private fun showDropdownTeganganBateraiUnit() {
        val etTeganganBateraiUnit = findViewById<AutoCompleteTextView>(R.id.et_tegangan_baterai_unit)

        val dataTeganganBaterai = resources.getStringArray(R.array.data_tegangan_baterai_unit)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataTeganganBaterai)
        etTeganganBateraiUnit.setAdapter(adapter)

        etTeganganBateraiUnit.setOnItemClickListener { adapterView, view, position, id ->
            val selectedValue = adapter.getItem(position)
            Toast.makeText(this, selectedValue, Toast.LENGTH_LONG).show()
        }
    }

    private fun showDropdownTeganganBaterai() {
        val etTeganganBaterai = findViewById<AutoCompleteTextView>(R.id.et_tegangan_baterai)

        val dataTeganganBaterai = resources.getStringArray(R.array.data_tegangan_baterai)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataTeganganBaterai)
        etTeganganBaterai.setAdapter(adapter)

        etTeganganBaterai.setOnItemClickListener { adapterView, view, position, id ->
            val selectedValue = adapter.getItem(position)
            Toast.makeText(this, selectedValue, Toast.LENGTH_LONG).show()
        }
    }
}