package com.monitoring.kalkulatorplts.app.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.monitoring.kalkulatorplts.R
import com.monitoring.kalkulatorplts.app.ui.advance.inputData.InputBebanAdvance
import com.monitoring.kalkulatorplts.app.ui.simple.InputBebanSimple

class GetStarted : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        val btn_mode_simple = findViewById(R.id.btn_mode_simple) as Button
        btn_mode_simple.setOnClickListener {
            val intent = Intent(this, InputBebanSimple::class.java)
            startActivity(intent)
        }

        val btn_mode_advance = findViewById(R.id.btn_mode_advance) as Button
        btn_mode_advance.setOnClickListener {
            val intent = Intent(this, InputBebanAdvance::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Tutup aplikasi")
            setMessage("Apakah kamu ingin keluar dari aplikasi?")

            setPositiveButton("Keluar") { _, _ ->
                // if user press yes, then finish the current activity
                finishAffinity()
            }

            setNegativeButton("Batal"){_, _ ->
                // if user press no, then return the activity
            }

            setCancelable(true)
        }.create().show()
    }




}