package com.monitoring.kalkulatorplts.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.monitoring.kalkulatorplts.app.data.BebanData
import com.monitoring.kalkulatorplts.app.data.Lokasi
import com.monitoring.kalkulatorplts.app.data.LokasiRepository

class ViewModel(application: Application) : AndroidViewModel(application){

    var listBeban= MutableLiveData<ArrayList<BebanData>>()
    val lokasiRepository:LokasiRepository = LokasiRepository()
    var newList = arrayListOf<BebanData>()
    var totalEnergyBatt= MutableLiveData<Int>()
    var totalEnergyPv = MutableLiveData<Int>()
    var totalDailyEnergy = MutableLiveData<Int>()
    var lokasiList = arrayListOf<Lokasi>()
    var daerah = MutableLiveData<ArrayList<String>>()
    var dataPsh = MutableLiveData<Double>()
    var hasilDec = MutableLiveData<Double>()
    var hasilKapasitasBatt = MutableLiveData<Double>()
    var hasilKapasitasInverter = MutableLiveData<Double>()
    var hasilKapasitasPvInverter = MutableLiveData<Double>()

    var energiHarian = 0
    var energyBattery = 0
    var energyPv = 0
    var dec = 0.0
    var kapasitasBatt = 0.0
    var kapasitasInverter = 0.0
    var kapasitasPvInverter = 0.0



    init {
        daerah = lokasiRepository.daerah
    }


    fun addBeban(bebanData: BebanData){
        newList.add(bebanData)
        listBeban.value = newList
    }

    fun remove(bebanData: BebanData){
        val dayaBeban = bebanData.dayaBeban.toInt()
        val durasiBeban = bebanData.durasiBeban.toInt()
        var energyPerHour = dayaBeban * durasiBeban
        newList.remove(bebanData)
        listBeban.value= newList
    }

    fun calculateEnergy(bebanData: BebanData) {
        val sumberBeban = bebanData.sumberBeban.toString()
        val dayaBeban = bebanData.dayaBeban.toInt()
        val durasiBeban = bebanData.durasiBeban.toInt()
        val energyPerHour = dayaBeban * durasiBeban
        if (sumberBeban == "Baterai"){
            energyBattery+=energyPerHour
            totalEnergyBatt.value = energyBattery
        }else{
            energyPv+=energyPerHour
            totalEnergyPv.value = energyPv
        }
        var totalEnergy = energyBattery + energyPv
        totalDailyEnergy.value = totalEnergy
    }

    fun convertEnergy(bebanData:String?){
        val jumlahHari = 30
        val formula = bebanData!!.toInt()/(1000*jumlahHari)
        energiHarian = formula
        totalDailyEnergy.value = formula
    }

    fun getPsh(position: Int){
        lokasiList = lokasiRepository.lokasiList
        var pshFound = lokasiList[position].PSH
        dataPsh.value = pshFound
    }

    fun calculateDec(efisiensi: String?, energyPv: String?, energyBatt: String?){
        var formula = energyPv!!.toDouble() + (energyBatt!!.toDouble()/(efisiensi!!.toDouble()/100))
        dec = formula
        hasilDec.value = dec
    }

    fun calculateKapasitasBatt(energyPv: String?, efisiensi: String?,
                               dodMax: String?, hariOtonom:String?){
        var formula = ((dec-energyPv!!.toDouble())/(efisiensi!!.toDouble()*dodMax!!.toDouble()/10000))*hariOtonom!!.toDouble()
        kapasitasBatt = formula
        hasilKapasitasBatt.value = kapasitasBatt

    }

    fun calculateKapasitasInverter(dataPsh: String?, rasioPv: String?){
        var formula = (dec*100/rasioPv!!.toDouble())/(dataPsh!!.toDouble()*0.75)
        kapasitasInverter = formula
        hasilKapasitasInverter.value = kapasitasInverter
    }

    fun calculateKapasitasPvInverter(rasioAcDc:String?){
        var fomula = kapasitasInverter*(rasioAcDc!!.toDouble()/100)
        kapasitasPvInverter = fomula
        hasilKapasitasPvInverter.value = kapasitasPvInverter

    }

    fun calculateDecSimple(){
        var formula = energiHarian/0.95
        dec = formula
        hasilDec.value = dec
    }

    fun calculateKapasitasBattSimple(){
        var formula = ((dec-energiHarian)/(0.95*0.2)*1)
        hasilKapasitasBatt.value = formula
    }

    fun calculateKapasitasInverterSimple(dataPsh: String?){
        var formula = (dec/0.75)/(dataPsh!!.toDouble()*0.75)
        kapasitasInverter = formula
        hasilKapasitasInverter.value = kapasitasInverter
    }

    fun calculateKapasitasPvInverterSimple() {
        var formula = kapasitasInverter*1.2
        hasilKapasitasPvInverter.value = formula
    }


}