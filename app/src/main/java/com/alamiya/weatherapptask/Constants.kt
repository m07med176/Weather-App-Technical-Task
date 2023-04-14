package com.alamiya.weatherapptask

object Constants {
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val API_KEY = "bbcb13e1d448621ffd8e565701972f6d"

    // Network and Room cash
    const val MAX_AGE = 7
    const val  MAX_AGE_MILLI = MAX_AGE * 24 * 60 * 60 * 1000
    const val MAX_STALE = 60 * 60 * 24 * 7
    const val WORKER_ID:Long = 2554
}