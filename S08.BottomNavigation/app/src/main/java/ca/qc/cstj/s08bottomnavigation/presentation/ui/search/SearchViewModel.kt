package ca.qc.cstj.s08bottomnavigation.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.s08bottomnavigation.core.LoadingResource
import ca.qc.cstj.s08bottomnavigation.data.repositories.MeteoRepository
import ca.qc.cstj.s08bottomnavigation.domain.models.Meteo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val meteoRepository = MeteoRepository()

    private val _meteo = MutableLiveData<LoadingResource<Meteo>>()
    val meteo : LiveData<LoadingResource<Meteo>> get() = _meteo

    private var searchJob: Job? = null

    fun search(cityName: String) {
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            meteoRepository.retrieve(cityName).collect {
                _meteo.value = it
            }
        }
    }


}