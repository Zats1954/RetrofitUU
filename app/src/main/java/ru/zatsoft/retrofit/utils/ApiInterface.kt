package ru.zatsoft.retrofit.utils

import retrofit2.Response
import retrofit2.http.GET
import ru.zatsoft.retrofit.CatImage

interface ApiInterface {
    @GET("woof.json?ref=apilist.fun")
    suspend fun getCatImage(): Response<CatImage>
}