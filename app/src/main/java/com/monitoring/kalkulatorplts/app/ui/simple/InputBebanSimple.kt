package com.monitoring.kalkulatorplts.app.ui.simple

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.databinding.ActivityInputBebanSimpleBinding

class InputBebanSimple : AppCompatActivity() {
    private lateinit var binding:ActivityInputBebanSimpleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBebanSimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnNext.setOnClickListener {
            showResultSimple()

        }

    }

    override fun onResume() {
        super.onResume()
        showDropdownACDC()
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
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)
        if(savedString.equals("AC")){
            val intent = Intent(this,HasilSimpleAC::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this,HasilSimpleDC::class.java)
            startActivity(intent)
        }


    }
}