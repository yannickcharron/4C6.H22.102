package ca.qc.cstj.s09navigationdrawer.presentation.ui.barcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.s09navigationdrawer.core.Constants
import ca.qc.cstj.s09navigationdrawer.core.Resource
import ca.qc.cstj.s09navigationdrawer.data.repositories.CheckInRepository
import ca.qc.cstj.s09navigationdrawer.domain.models.CheckIn
import kotlinx.coroutines.launch

class BarcodeViewModel : ViewModel() {

    private val checkInRepository = CheckInRepository()

    private val _checkIn = MutableLiveData<Resource<CheckIn>>()
    val checkIn: LiveData<Resource<CheckIn>> get() = _checkIn

    fun addCheckIn(codeValue: String) {
        viewModelScope.launch {
            _checkIn.value = checkInRepository.create(CheckIn(codeValue, Constants.DOOR))
        }
    }
}