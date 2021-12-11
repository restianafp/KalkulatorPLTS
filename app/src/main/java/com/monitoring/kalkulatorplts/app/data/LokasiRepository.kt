package com.monitoring.kalkulatorplts.app.data

import androidx.lifecycle.MutableLiveData

class LokasiRepository {
    var lokasiList = arrayListOf<Lokasi>()
    var daerah = MutableLiveData<ArrayList<String>>()

    init {
        populateList()
        getDaerah()
    }


    private fun populateList(){
        lokasiList.add(Lokasi("Yogyakarta",5.0))
        lokasiList.add(Lokasi("Bantul", 5.1))
        lokasiList.add(Lokasi("Sleman", 4.5))
        lokasiList.add(Lokasi("Gunungkidul", 5.1))
        lokasiList.add(Lokasi("Kulon Progo", 4.8))

    }

    private fun getDaerah(){
        val daerahList = arrayListOf<String>()
        lokasiList.forEach {
            daerahList.add(it.daerah)
            daerah.value = daerahList}
    }
}