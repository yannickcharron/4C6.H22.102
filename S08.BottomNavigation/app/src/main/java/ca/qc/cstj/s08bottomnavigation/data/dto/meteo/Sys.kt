package ca.qc.cstj.s08bottomnavigation.data.dto.meteo

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)