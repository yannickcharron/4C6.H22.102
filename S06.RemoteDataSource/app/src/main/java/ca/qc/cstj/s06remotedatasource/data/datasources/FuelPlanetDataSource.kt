package ca.qc.cstj.s06remotedatasource.data.datasources

import ca.qc.cstj.s06remotedatasource.core.Constants
import ca.qc.cstj.s06remotedatasource.domain.models.Planet
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class FuelPlanetDataSource {

    suspend fun retrieveAll() : List<Planet> {

        return withContext(Dispatchers.IO) {
            val (req, res, result) = Constants.PLANET_API_URL.httpGet().responseJson()

            when(result) {
                // Code dans la famille 200
                is Result.Success -> {
                    val planets = mutableListOf<Planet>()
                    val planetsJson = result.value.array()

                    for(i in 0 until planetsJson.length()) {
                        planets.add(deserializePlanet(planetsJson.getJSONObject(i)))
                    }
                    planets

                }
                // Code dans les familles 400 ou 500
                is Result.Failure -> {
                    throw result.error.exception
                }
            }
        }

    }

    private fun deserializePlanet(planetJson: JSONObject) : Planet {

        val name = planetJson.getString("name")
        val temperature = planetJson.getDouble("temperature")
        val image = planetJson.getString("icon")

        return Planet(name, image, temperature)
    }

}