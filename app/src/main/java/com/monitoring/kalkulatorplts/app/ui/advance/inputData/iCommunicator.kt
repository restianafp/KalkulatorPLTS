package com.monitoring.kalkulatorplts.app.ui.advance.inputData

import com.monitoring.kalkulatorplts.app.data.BebanData

interface iCommunicator {
    fun getBeban(bebanData: BebanData)
}