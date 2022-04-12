package ca.qc.cstj.s08bottomnavigation.data.datasources

import ca.qc.cstj.s08bottomnavigation.core.Constants
import ca.qc.cstj.s08bottomnavigation.data.dto.meteo.MeteoDTO
import ca.qc.cstj.s08bottomnavigation.domain.models.Meteo
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MeteoDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun retrieve(cityName: String) : Meteo {

        return withContext(Dispatchers.IO) {
           val serviceURL = Constants.METEO_RETRIEVE_API_URL.format(cityName)

           val (_, _, result) = serviceURL.httpGet().responseJson()

           when(result) {
               is Result.Success -> {
                    val meteoDTO = json.decodeFromString<MeteoDTO>(result.value.content)

                   return@withContext Meteo(meteoDTO.name,
                       meteoDTO.sys.country,
                       meteoDTO.main.temp,
                       meteoDTO.weather[0].main,
                       meteoDTO.dt,
                       meteoDTO.timezone,
                       meteoDTO.coord.lat,
                       meteoDTO.coord.lon)
               }
               is Result.Failure -> {
                   throw result.error.exception
               }
           }
        }

    }

}