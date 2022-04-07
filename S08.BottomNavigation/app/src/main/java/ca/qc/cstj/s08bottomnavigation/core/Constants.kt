package ca.qc.cstj.s08bottomnavigation.core

object Constants {

    const val METEO_REFRESH_INTERVAL: Long = 120000
    const val API_KEY = "124312b73ab06cec2dce4a5d8c9e2637"
    const val METEO_RETRIEVE_API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=${API_KEY}&units=metric"

}