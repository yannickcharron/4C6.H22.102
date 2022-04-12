package ca.qc.cstj.s08bottomnavigation.data.dto.meteo

import kotlinx.serialization.Serializable

@Serializable
data class MeteoDTO(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)