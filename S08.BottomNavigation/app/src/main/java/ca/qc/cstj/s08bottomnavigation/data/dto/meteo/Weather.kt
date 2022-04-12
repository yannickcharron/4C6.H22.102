package ca.qc.cstj.s08bottomnavigation.data.dto.meteo

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)