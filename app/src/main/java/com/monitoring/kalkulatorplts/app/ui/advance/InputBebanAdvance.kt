package com.monitoring.kalkulatorplts.app.ui.advance

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.app.ui.advance.model.BebanData
import com.monitoring.kalkulatorplts.app.ui.advance.view.BebanAdapter
import com.monitoring.kalkulatorplts.databinding.ActivityInputBebanAdvanceBinding

class InputBebanAdvance : AppCompatActivity() {
    private lateinit var binding: ActivityInputBebanAdvanceBinding
    private lateinit var bebanList: ArrayList<BebanData>
    private lateinit var bebanAdapter: BebanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBebanAdvanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bebanList = ArrayList()
        bebanAdapter = BebanAdapter(this,bebanList)
        binding.rvBeban.layoutManager = LinearLayoutManager(this)
        binding.rvBeban.adapter = bebanAdapter

        binding.btnNext.setOnClickListener {
            val intent = Intent(this,InputDataAdvance::class.java)
            startActivity(intent)
        }

        binding.btnAddDevice.setOnClickListener {
            addBeban()
        }
    }

    private fun addBeban() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.fragment_tambah_beban_dialog, null)
        val etJenis = mDialogView.findViewById<TextInputEditText>(R.id.et_jenis_beban)
        val etSumber = mDialogView.findViewById<AutoCompleteTextView>(R.id.et_sumber_beban)
        val etDaya = mDialogView.findViewById<TextInputEditText>(R.id.et_daya_beban)
        val etDurasi = mDialogView.findViewById<TextInputEditText>(R.id.et_durasi_beban)
        val addBtn = mDialogView.findViewById<Button>(R.id.btn_tambah)
        val cancelBtn = mDialogView.findViewById<Button>(R.id.btn_batal)
        val dataRasioACDC = resources.getStringArray(R.array.data_rasio_acdc)
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, dataRasioACDC)
        val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
        val addDialog = mBuilder.create()
        addDialog.show()
        etSumber.setAdapter(adapter)

        addBtn.setOnClickListener{
            val jenis = etJenis.text.toString()
            val sumber = etSumber.text.toString()
            val daya = etDaya.text.toString()
            val durasi = etDurasi.text.toString()
            bebanList.add(BebanData(jenis, sumber, daya, durasi))
            bebanAdapter.notifyDataSetChanged()
            addDialog.dismiss()
        }

        cancelBtn.setOnClickListener {
            addDialog.dismiss()
        }

    }
}