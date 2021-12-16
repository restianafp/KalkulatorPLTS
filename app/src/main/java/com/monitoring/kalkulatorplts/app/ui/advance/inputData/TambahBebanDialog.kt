package com.monitoring.kalkulatorplts.app.ui.advance.inputData

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.DialogFragment
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.app.data.BebanData
import com.monitoring.kalkulatorplts.app.ui.ViewModel
import com.monitoring.kalkulatorplts.databinding.FragmentTambahBebanDialogBinding


class TambahBebanDialog : DialogFragment() {

    companion object{
        const val TAG = "TambahBebanDialog"
    }
    private lateinit var  binding:FragmentTambahBebanDialogBinding


 

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTambahBebanDialogBinding.inflate(layoutInflater,container,false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val communicator = activity as iCommunicator

        binding.btnTambah.setOnClickListener {
            if (isFormNotEmpty() && isFormNumeric()){
                val jenisBeban = binding.etJenisBeban.text.toString()
                val sumberBeban = binding.etSumberBeban.text.toString()
                val dayaBeban = binding.etDayaBeban.text.toString()
                val durasiBeban = binding.etDurasiBeban.text.toString()
                val beban = BebanData(jenisBeban, sumberBeban, dayaBeban, durasiBeban)
                communicator.getBeban(beban)
                dialog?.dismiss()
            }

        }

        binding.btnBatal.setOnClickListener {
            dialog?.dismiss()
        }


    }

    override fun onResume() {
        super.onResume()
        showDropdownSumber()
    }

    private fun showDropdownSumber() {

        val dataSumber = resources.getStringArray(R.array.sumber)
        val adapter =  activity?.let {
            ArrayAdapter(it, R.layout.item_dropdown, dataSumber)

        }
        binding.etSumberBeban.setAdapter(adapter)
    }

    private fun isFormNotEmpty(): Boolean {
        with(binding){
            if(etJenisBeban.text.isNullOrEmpty()){
                etJenisBeban.error = "Isi Jenis Beban"
                return false
            }
            if (etSumberBeban.text.isNullOrEmpty()){
                etSumberBeban.error="Isi Sumber Beban"
                return false
            }
            if(etDayaBeban.text.isNullOrEmpty()){
                etDayaBeban.error = "Isi Daya Beban"
                return false
            }
            if(etDurasiBeban.text.isNullOrEmpty()){
                etDurasiBeban.error = "Isi Durasi Beban"
                return false
            }
            return true
        }

    }

    private fun isFormNumeric(): Boolean {
        with(binding){
            if(etDayaBeban.text?.isDigitsOnly() == false){
                etDayaBeban.error = "Masukkan input angka"
                return false
            }
            if (etDurasiBeban.text?.isDigitsOnly() == false){
                etDurasiBeban.error = "Masukkan input angka"
                return false
            }
            return true
        }



    }

}