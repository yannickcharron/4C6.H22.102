package ca.qc.cstj.s06remotedatasource.data.repositories

import ca.qc.cstj.s06remotedatasource.core.Resource
import ca.qc.cstj.s06remotedatasource.data.datasources.FuelPlanetDataSource
import ca.qc.cstj.s06remotedatasource.domain.models.Planet

class FuelPlanetRepository {

    private val planetDataSource = FuelPlanetDataSource()

    suspend fun retrieveAll(): Resource<List<Planet>> {
        //Appel du DataSource
        return try {
            Resource.Success(planetDataSource.retrieveAll())
        } catch(ex: Exception) {
            Resource.Error(ex, "Erreur serveur")
        }
    }

}