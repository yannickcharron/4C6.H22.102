package ca.qc.cstj.s08bottomnavigation.data.dto.meteo

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)