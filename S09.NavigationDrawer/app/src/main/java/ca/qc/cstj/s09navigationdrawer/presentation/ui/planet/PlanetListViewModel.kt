package ca.qc.cstj.s09navigationdrawer.presentation.ui.planet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.s09navigationdrawer.core.LoadingResource
import ca.qc.cstj.s09navigationdrawer.data.repositories.PlanetRepository
import ca.qc.cstj.s09navigationdrawer.domain.models.Planet
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlanetListViewModel : ViewModel() {

    private val planetRepository = PlanetRepository()

    private val _planets = MutableLiveData<LoadingResource<List<Planet>>>()
    val planets : LiveData<LoadingResource<List<Planet>>> get() = _planets

    init {
        refreshPlanets()
    }

    fun refreshPlanets() {
        viewModelScope.launch {
            planetRepository.retrieveAll().collect {
                _planets.value = it
            }
        }
    }

}