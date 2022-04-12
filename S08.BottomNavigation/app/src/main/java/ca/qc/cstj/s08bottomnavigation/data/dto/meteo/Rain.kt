package ca.qc.cstj.s08bottomnavigation.data.dto.meteo

import kotlinx.serialization.Serializable

@Serializable
data class Rain(
    val `1h`: Double
)