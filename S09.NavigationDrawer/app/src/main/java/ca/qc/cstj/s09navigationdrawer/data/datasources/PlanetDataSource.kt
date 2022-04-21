package ca.qc.cstj.s09navigationdrawer.data.datasources

import ca.qc.cstj.s09navigationdrawer.core.Constants
import ca.qc.cstj.s09navigationdrawer.domain.models.Planet
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class PlanetDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun retrieveAll() : List<Planet> {

        return withContext(Dispatchers.IO) {
            val (_, _, result) = Constants.PLANET_API_URL.httpGet().responseJson()
            when(result) {
                is Result.Success -> {
                    //val planets : List<Planet> = json.decodeFromString(result.value.content)

                    return@withContext json.decodeFromString(result.value.content)

                }
                is Result.Failure -> {
                    throw result.error.exception

                }
            }
        }
    }

    suspend fun retrieve(href: String) : Planet {
        TODO()
    }

}