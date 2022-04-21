package ca.qc.cstj.s09navigationdrawer.data.repositories

import ca.qc.cstj.s09navigationdrawer.core.Constants
import ca.qc.cstj.s09navigationdrawer.core.LoadingResource
import ca.qc.cstj.s09navigationdrawer.core.Resource
import ca.qc.cstj.s09navigationdrawer.data.datasources.PlanetDataSource
import ca.qc.cstj.s09navigationdrawer.domain.models.Planet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlanetRepository {

    private val planetDataSource = PlanetDataSource()

    suspend fun retrieveAll() : Flow<LoadingResource<List<Planet>>> {
        return flow {
            while(true) {
                try {
                    emit(LoadingResource.Loading())
                    //delay(1500)
                    emit(LoadingResource.Success(planetDataSource.retrieveAll()))
                } catch (ex: Exception) {
                    emit(LoadingResource.Error(ex, ex.message))
                }
                delay(Constants.REFRESH_PLANET_DELAY)
            }
        }
    }

    suspend fun retrieve(href: String) : Resource<Planet> {
        TODO()
    }
}