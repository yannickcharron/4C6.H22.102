package ca.qc.cstj.s08bottomnavigation.presentation.ui.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    private var _count = MutableLiveData<Int>(0)
    val count get() = _count

    fun add() {
        _count.value = _count.value!! + 1
    }

}