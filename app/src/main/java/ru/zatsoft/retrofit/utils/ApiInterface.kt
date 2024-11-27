package ru.zatsoft.retrofit.utils

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.zatsoft.retrofit.models.CurrentWeather

interface ApiInterface {
    @GET("weather?")
    suspend fun getCurrentWeather(
        @Query("q") city:String,
        @Query("units") units:String,
        @Query("appid") appid:String
    ): Response<CurrentWeather>
}