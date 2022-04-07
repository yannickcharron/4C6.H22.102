package ca.qc.cstj.s08bottomnavigation.data.repositories

import ca.qc.cstj.s08bottomnavigation.core.Constants
import ca.qc.cstj.s08bottomnavigation.core.LoadingResource
import ca.qc.cstj.s08bottomnavigation.data.datasources.MeteoDataSource
import ca.qc.cstj.s08bottomnavigation.domain.models.Meteo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MeteoRepository {

    private val meteoDataSource = MeteoDataSource()

    fun retrieve(cityName: String) : Flow<LoadingResource<Meteo>> {

        return flow {
            while(true) {
                try {
                    emit(LoadingResource.Loading())
                    emit(LoadingResource.Success(meteoDataSource.retrieve(cityName)))
                } catch(ex:Exception){
                    emit(LoadingResource.Error(ex, ex.message))
                }
                delay(Constants.METEO_REFRESH_INTERVAL)
            }
        }

    }

}