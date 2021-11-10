package com.monitoring.kalkulatorplts.app.ui.advance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.databinding.ActivityInputBebanAdvanceBinding
import com.monitoring.kalkulatorplts.databinding.ActivityInputDataAdvanceBinding

class InputBebanAdvance : AppCompatActivity() {
    private lateinit var binding: ActivityInputBebanAdvanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBebanAdvanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this,InputDataAdvance::class.java)
            startActivity(intent)
        }

        binding.btnAddDevice.setOnClickListener {
            showAddDeviceDialog()
        }
    }

    private fun showAddDeviceDialog() {
        val addDeviceDialogFragment = TambahBebanDialog()
        addDeviceDialogFragment.show(supportFragmentManager,TambahBebanDialog::class.java.simpleName)
    }
}