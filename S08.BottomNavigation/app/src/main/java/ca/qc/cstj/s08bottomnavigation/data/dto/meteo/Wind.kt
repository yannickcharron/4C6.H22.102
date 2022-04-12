package ca.qc.cstj.s08bottomnavigation.data.dto.meteo

import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    val deg: Int,
    val speed: Double
)