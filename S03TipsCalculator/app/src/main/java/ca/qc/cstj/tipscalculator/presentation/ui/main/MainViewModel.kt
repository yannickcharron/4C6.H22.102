package ca.qc.cstj.tipscalculator.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.qc.cstj.tipscalculator.domain.models.TipsCalculation

class MainViewModel: ViewModel() {

    private val _tipsCalculation = MutableLiveData<TipsCalculation>()
    val tipsCalculation: LiveData<TipsCalculation>
        get() = _tipsCalculation

    fun calculate(subtotal:Double, tipsPercent: Int) {
        _tipsCalculation.value = TipsCalculation(subtotal, tipsPercent)
    }

}