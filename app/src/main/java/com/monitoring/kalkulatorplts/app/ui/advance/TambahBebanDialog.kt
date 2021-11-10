package com.monitoring.kalkulatorplts.app.ui.advance

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.databinding.FragmentTambahBebanDialogBinding


class TambahBebanDialog : DialogFragment() {
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

}