package ru.zatsoft.retrofit.utils

import retrofit2.Response
import retrofit2.http.GET
import ru.zatsoft.retrofit.CatImage

interface ApiInterface {
    @GET("v1/images/search")
    suspend fun getCatImage(): Response<List<CatImage>>
}